package com.senlin.jvm.heap.allocation;

/**
 * 对象优先分配到 Eden 区，当 Eden 区没有足够空间时，虚拟机发起一次 GC。
 *
 * @author gsl
 * @date 2018/6/14 8:28.
 */
public class PriorityAllocationEden {

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
        byte[] arr1 = new byte[2 * MB_1];
        byte[] arr2 = new byte[2 * MB_1];
        byte[] arr3 = new byte[2 * MB_1];
        byte[] arr4 = new byte[4 * MB_1];
    }

    /*
   0.322: [GC (Allocation Failure) [PSYoungGen: 6585K->840K(9216K)] 6585K->4944K(19456K), 0.0034194 secs] [Times: user=0.06 sys=0.00, real=0.00 secs]
    Heap
     PSYoungGen      total 9216K, used 7221K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
      eden space 8192K, 77% used [0x00000000ff600000,0x00000000ffc3b798,0x00000000ffe00000)
      from space 1024K, 82% used [0x00000000ffe00000,0x00000000ffed2020,0x00000000fff00000)
      to   space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
     ParOldGen       total 10240K, used 4104K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
      object space 10240K, 40% used [0x00000000fec00000,0x00000000ff002020,0x00000000ff600000)
     Metaspace       used 3465K, capacity 4496K, committed 4864K, reserved 1056768K
      class space    used 379K, capacity 388K, committed 512K, reserved 1048576K
     */

    /*
      该GC 日志为 虚拟机为JDK8，GC分析：
      PSYoungGen 说明，年轻代采用 Parallel Scavenge 收集器，年轻代总容量为 9216K（eden 8192K + 一半的 Survivor 区 1024K）。
      ParOldGen 说明，老年代采用 Parallel Old 收集器，总容量为 10240K 。

      当个分配 arr1、arr2、arr3 时，都是直接在 Eden 区分配的，此时 新生代的内存使用 2M + 2M + 2M 剩余 3M = (9M - 2M - 2M - 2M)，
      当分配 arr4 时 发生了 Allocation Failure，触发了一次 GC 操作，新生代从 6585K 减少到 840K ，而总的堆内存几乎没有变化 6585K 减少到 4944K 。
      然后在 ParOldGen 老年代分配了 4m 的 arr4 ，我们发现在 Parallel Scavange 收集下，大对象直接进入老年代了。
      最终的 GC 分配为：arr1、arr2、arr3 在 PSYoungGen 上分配，arr4 在 ParOldGen 上分配。

      在Jdk 7 中使用 Serial、PerNew 收集器，可以通过设置 -XX:PretenureSizeThreshold 参数令大于这个设置值的对象直接在老年代分配。
     */
}
