package com.senlin.jvm.gc;

/**
 * Java 虚拟机采用 可达性分析算法判断 对象是否可被 GC 回收
 *
 * @author gsl
 * @date 2018/6/10 23:07.
 */
public class ReferenceGC {

    public Object instance = null;

    private byte[] byteArr = new byte[1024 * 1024 * 20];

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
    -XX:SurvivorRatio=8
     */

    public static void main(String[] args) {
        ReferenceGC objA = new ReferenceGC();
        ReferenceGC objB = new ReferenceGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        //触发 GC
        System.gc();
    }

    /*
    Java HotSpot(TM) 64-Bit Server VM (25.151-b12) for windows-amd64 JRE (1.8.0_151-b12), built on Sep  5 2017 19:33:46 by "java_re" with MS VC++ 10.0 (VS2010)
    Memory: 4k page, physical 8302960k(3367660k free), swap 16604044k(8188392k free)
    CommandLine flags: -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=F:\spring_source\dump_dir -XX:InitialHeapSize=104857600 -XX:MaxHeapSize=104857600 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC
    2018-06-10T23:20:44.037+0800: 0.591: [GC (System.gc()) [PSYoungGen: 22691K->696K(30720K)] 43171K->21184K(99328K), 0.0134453 secs] [Times: user=0.00 sys=0.00, real=0.01 secs]
    2018-06-10T23:20:44.051+0800: 0.604: [Full GC (System.gc()) [PSYoungGen: 696K->0K(30720K)] [ParOldGen: 20488K->596K(68608K)] 21184K->596K(99328K), [Metaspace: 2856K->2856K(1056768K)], 0.0263774 secs] [Times: user=0.02 sys=0.00, real=0.03 secs]
    Heap
     PSYoungGen      total 30720K, used 276K [0x00000000fdf00000, 0x0000000100000000, 0x0000000100000000)
      eden space 27648K, 1% used [0x00000000fdf00000,0x00000000fdf45360,0x00000000ffa00000)
      from space 3072K, 0% used [0x00000000ffa00000,0x00000000ffa00000,0x00000000ffd00000)
      to   space 3072K, 0% used [0x00000000ffd00000,0x00000000ffd00000,0x0000000100000000)
     ParOldGen       total 68608K, used 596K [0x00000000f9c00000, 0x00000000fdf00000, 0x00000000fdf00000)
      object space 68608K, 0% used [0x00000000f9c00000,0x00000000f9c95260,0x00000000fdf00000)
     Metaspace       used 2863K, capacity 4486K, committed 4864K, reserved 1056768K
      class space    used 306K, capacity 386K, committed 512K, reserved 1048576K
     */

    /*
     GC 日志分析：
        第 45行 [GC (System.gc()) [PSYoungGen: 22691K->696K(30720K)] 43171K->21184K(99328K), 0.0134453 secs]
        1. 年轻代从 22691K 减少到 696K，年轻代总大小为 (30720K)
        2. 堆内存 从 43171K 减少到 21184K 堆的总大小为 (99328K)
        3. 本次执行垃圾收集时间为 0.0134453 secs
     */
}
