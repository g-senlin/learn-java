package com.senlin.zookeeper.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author gsl
 * @date 2018/11/15 23:25.
 */
@Slf4j
public class ZookeeperUsageTest {
    /**
     * zookeeper connectString
     */
    private static final String connectString = "192.168.40.10:2181";

    @Test
    public void testCreateZookeeperSession() throws IOException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 10000,
                event -> {
                    log.info("-- zookeeper server connecting success");
                    // -- zookeeper event: WatchedEvent state:SyncConnected type:None path:null
                    log.info("-- zookeeper event: {}", event.toString());
                    if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                        countDownLatch.countDown();
                    }
                });
        // -- after new zookeeper of state: CONNECTING
        log.info("-- after new zookeeper of state: {}", zooKeeper.getState());
        countDownLatch.await();
        log.info("-- end");
    }

    @Test
    public void testCreateZookeeperSessionWithSessionId() throws IOException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 10000,
                event -> {
                    log.info("-- zookeeper server connecting success");
                    // -- zookeeper event: WatchedEvent state:SyncConnected type:None path:null
                    log.info("-- zookeeper event: {}", event.toString());
                    if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                        countDownLatch.countDown();
                    }
                });
        // -- after new zookeeper of state: CONNECTING
        log.info("-- after new zookeeper of state: {}", zooKeeper.getState());
        countDownLatch.await();
        long sessionId = zooKeeper.getSessionId();
        // -- sessionId：72058987202150431
        log.info("-- sessionId：{}", sessionId);
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();
        //  -- sessionPasswd：[-29, 127, 45, -116, -12, -58, 86, 12, -113, 42, 55, 18, 91, 61, -96, 55]
        log.info("-- sessionPasswd：{}", sessionPasswd);

        CountDownLatch countDownLatch1 = new CountDownLatch(1);
        ZooKeeper zooKeeperWithSessionId = new ZooKeeper(connectString, 10000,
                event -> {
                    log.info("-- zookeeper server connecting success");
                    // -- zookeeper event: WatchedEvent state:SyncConnected type:None path:null
                    log.info("-- zookeeper event: {}", event.toString());
                    if (Watcher.Event.KeeperState.SyncConnected == event.getState()) {
                        countDownLatch1.countDown();
                    }
                }, sessionId, sessionPasswd);
        countDownLatch1.await();
        long sessionId1 = zooKeeperWithSessionId.getSessionId();
        // -- sessionId1：72058987202150431
        log.info("-- sessionId1：{}", sessionId1);
        byte[] sessionPasswd1 = zooKeeperWithSessionId.getSessionPasswd();
        // -- sessionPasswd1：[-29, 127, 45, -116, -12, -58, 86, 12, -113, 42, 55, 18, 91, 61, -96, 55]
        log.info("-- sessionPasswd1：{}", sessionPasswd1);
        log.info("-- end");
    }

    @Test
    public void testSyncCreateNode() throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 10000,
                event -> log.info("-- zookeeper event: {}", event.toString()));
        /*
         * CreateMode：
         *  CreateMode.PERSISTENT  创建持久化节点
         *  CreateMode.EPHEMERAL 创建临时节点，临时节点的生命周期与 seesion 一致
         *  CreateMode.PERSISTENT_SEQUENTIAL 创建持久化有序节点
         *  CreateMode.EPHEMERAL_SEQUENTIAL 创建临时有序节点
         */

        // 同步创建临时节点
        String ephemeralPath = zooKeeper.create("/gsl-", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        // -- ephemeralPath: /gsl-
        log.info("-- ephemeralPath: {}", ephemeralPath);

        // Zookeeper 创建顺序节点会自动在节点后加上一个数字，并且在 api 接口返回值中返回该数数据节点的一个完整节点路径
        String ephemeralSequentialPath = zooKeeper.create("/gsl-", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        // -- ephemeralSequentialPath: /gsl-0000000021
        log.info("-- ephemeralSequentialPath: {}", ephemeralSequentialPath);
    }

    @Test
    public void testAsyncCreateNode() throws IOException, KeeperException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(connectString, 100000,
                event -> log.info("-- zookeeper event: {}", event.toString()));

        zooKeeper.create("/gsl", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, new AsyncCallback.StringCallback() {
            //                服务端节点创建完毕后回调方法
            @Override
            public void processResult(int rc, String path, Object ctx, String name) {
                if (rc == KeeperException.Code.OK.intValue()) {
//                     -- 执行服务端节点创建完毕后回调方法，create node name：/gsl0000000019
                    log.info("-- 执行服务端节点创建完毕后回调方法，create node name：{}", name);
                }
                countDownLatch.countDown();
            }
        }, new String("context"));
        countDownLatch.await();
        log.info("-- execute end");
    }
}