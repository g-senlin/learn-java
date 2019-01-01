package com.senlin.performance.Controller;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author gsl
 * @date 2018/12/17 6:44.
 */
@RestController
public class LockController {

    private Object lockA = new Object();

    private Object lockB = new Object();

    private List<Object> memory = new ArrayList<>();

    @GetMapping("/")
    public String sayHello(@RequestParam(required = false, value = "msg") String msg) {
        return StringUtils.hasText(msg) ? msg : "hello";
    }

    @GetMapping("/cpu")
    public void cpu() {
        System.out.println("cpu ok");
        while (true) {

        }
    }

    @GetMapping("/daedlock")
    public void daedLock() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + "enter lockA");
                    try {
                        TimeUnit.SECONDS.sleep(2L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (lockB) {
                        System.out.println(Thread.currentThread().getName() + "enter lockB");
                    }
                }
            }
        }, "A thread").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "enter lockB");
                    try {
                        TimeUnit.SECONDS.sleep(2L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (lockA) {
                        System.out.println(Thread.currentThread().getName() + "enter lockA");
                    }
                }
            }
        }, "B thread").start();
    }


    /*
     -XX:+HeapDumpOnOutOfMemoryError
     -XX:HeapDumpPath=F:\spring_source\dump_dir
     -Xloggc:F:\spring_source\gc_dir\gc.log  GC 输出到日志文件
     -XX:+PrintGCDetails 输出 GC 的详细日志
     -XX:+PrintGCTimeStamps  输出 GC 的时间戳（以基准时间的形式）
     -XX:+PrintGCDateStamps  输出 GC 的时间戳（以日期的形式，如 2018-06-10T23:20:44.037+0800）
     -verbose:gc 输出 GC 冗余信息
     -Xmx100m
     -Xms100m
     -Xmn10m 指定新生代大小
     -XX:SurvivorRatio=8


      因为方法区用于存放 Class 的相关信息，如类名、访问修饰符、字段描述，方法描述、常量池等，所以通过运行时生成大量的类去填满方法区.
    -XX:MaxMetaspaceSize=20m 指定元最大值
    -XX:MetaspaceSize=20m 指定元空间最小值
    jdk8 对应元空间内存溢出
    //java.lang.OutOfMemoryError: Metaspace


     通过 NIO 的 ByteBuffer 分配直接内存
    -XX:MaxDirectMemorySize=10m 指定直接内存最大值
    jdk8 对应元空间内存溢出
    // java.lang.OutOfMemoryError: Direct buffer memory

    当 jconsole 连接不上时，通过指定下列 JVM 参数连接
    -Dcom.sun.management.jmxremote
    -Dcom.sun.management.jmxremote.port=8011
    -Dcom.sun.management.jmxremote.ssl=false
    -Dcom.sun.management.jmxremote.authenticate=false
      */
    @GetMapping("/memory/{size}")
    public String memory(@PathVariable("size") Integer size) {
        byte[] array = new byte[1024 * 1024 * size];
        memory.add(array);
        return "consume  " + size +"M Memory ok";
    }

}
