package com.senlin.jvm.heap.allocation;

/**
 * 验证新生代对象进入老年代阈值 -XX:MaxTenuringThreshold=10
 * @author gsl
 * @date 2018/6/15 8:33.
 */
public class TenuringThreshold {

    private static final int MB_1 = 1024 * 1024;

    /*
    -XX:+PrintGCDetails 打印gc详细日志，并在退出时输出当前内存各个区域分配情况
    -verbose:gc 打印垃圾回收日志
    -Xmx20m 指定堆的最大值
    -Xms20m 指定堆最小值
    -Xmn10m 指定新生代大小
    -XX:SurvivorRatio=8 指定新生带 Eden 区与 Survivor 区的比例
     */

    public static void main(String[] args) {
        byte[] arr1 = new byte[1/4 * MB_1];
        byte[] arr2 = new byte[ 2* MB_1];
        byte[] arr3 = new byte[2 * MB_1];
//        arr3 = null;
        byte[] arr4 = new byte[4 * MB_1];
    }
}
