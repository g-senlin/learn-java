package com.senlin.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟运行时常量池内存溢出
 *
 * @author gsl
 * @date 2018/6/9 23:54.
 */
public class RunTimePoolOutOfMemory {

       /*
   JVM 参数
    -XX:+HeapDumpOnOutOfMemoryError 内存溢出时 dump 堆转储快照
    -XX:HeapDumpPath=F:\spring_source\dump_dir 指 dump 堆转储快照文件目录
    -XX:+PrintGCDetails 输出 gc 详细信息
    -XX:+PrintGCTimeStamps 输出 gc
    -verbose:gc 输出 gc 信息

    因为常量池是在元空间内存内，所以指定 元空间大小验证常量池内存溢出
    -XX:MaxMetaspaceSize=20m 指定元最大值
    -XX:MetaspaceSize=20m 指定元空间最小值

    jdk8 对应元空间内存溢出
    //java.lang.OutOfMemoryError: Metaspace
    */

    public static void main(String[] args) throws InterruptedException {
        // 使用 list 保持着对字符串的引用，避免 Full GC 回收常量池行为.
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            // intern() 方法先判断常量池中是否已存在该字符串，存在则直接返回字符串所在引用地址，不存在则先将该字符串加入常量池再返回引用地址.
            // 注意：这里放入常量池的字符串 ++i 必须是不同字符串。
            list.add(String.valueOf(++i).intern());
        }
    }
}
