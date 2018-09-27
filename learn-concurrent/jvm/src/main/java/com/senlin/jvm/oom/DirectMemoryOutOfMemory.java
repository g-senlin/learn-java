package com.senlin.jvm.oom;

import java.nio.ByteBuffer;

/**
 * 模拟 直接内存溢出
 *
 * @author gsl
 * @date 2018/6/10 21:08.
 */
public class DirectMemoryOutOfMemory {

    /*
     -XX:+HeapDumpOnOutOfMemoryError 内存溢出时 dump 堆转储快照
    -XX:HeapDumpPath=F:\spring_source\dump_dir 指 dump 堆转储快照文件目录
    -XX:+PrintGCDetails 输出 gc 详细信息
    -XX:+PrintGCTimeStamps 输出 gc
    -verbose:gc 输出 gc 信息

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

    public static void main(String[] args) {
        ByteBuffer.allocateDirect(11 * 1024 * 1024);
    }
}
