package com.senlin.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟堆内存溢出
 *
 * @author gsl
 * @date 2018/6/9 20:27.
 */
public class HeapOutOfMemory {

   /*
   JVM 参数
    -XX:+HeapDumpOnOutOfMemoryError 内存溢出时 dump 堆转储快照
    -XX:HeapDumpPath=F:\spring_source\dump_dir 指 dump 堆转储快照文件目录
    -XX:+PrintGCDetails 输出 gc 详细信息
    -XX:+PrintGCTimeStamps 输出 gc
    -verbose:gc 输出 gc 信息

    -Xmx20m 指定最大堆内存
    -Xms20m 指定最小堆内存
    */

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        while (true) {
            personList.add(new Person());
        }
    }

    private static class Person {
        private String name;
        private Integer age;
    }
}
