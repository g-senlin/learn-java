package com.senlin.jvm.oom;

/**
 * 模拟 栈内存溢出
 *
 * @author gsl
 * @date 2018/6/9 23:15.
 */
public class StackOverflow {

    private static int i;

    /*
   JVM 参数
    -XX:+HeapDumpOnOutOfMemoryError 内存溢出时 dump 堆转储快照
    -XX:HeapDumpPath=F:\spring_source\dump_dir 指 dump 堆转储快照文件目录
    -XX:+PrintGCDetails 输出 gc 详细信息
    -XX:+PrintGCTimeStamps 输出 gc
    -verbose:gc 输出 gc 信息

    -Xss128k 指定栈大小

    //java.lang.StackOverflowError
    */

    private static void recursiveInvocation() {
        i = ++i;
        recursiveInvocation();
    }

    public static void main(String[] args) {
        try {
            recursiveInvocation();
        } catch (Throwable e) {
            System.out.println("栈深度：" + i);
            e.printStackTrace();
        }
    }
}
