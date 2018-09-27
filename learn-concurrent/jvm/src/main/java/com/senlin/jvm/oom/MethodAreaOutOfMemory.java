package com.senlin.jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 模拟方法区内存溢出
 *
 * @author gsl
 * @date 2018/6/10 20:25.
 */
public class MethodAreaOutOfMemory {


    /*
     -XX:+HeapDumpOnOutOfMemoryError 内存溢出时 dump 堆转储快照
    -XX:HeapDumpPath=F:\spring_source\dump_dir 指 dump 堆转储快照文件目录
    -XX:+PrintGCDetails 输出 gc 详细信息
    -XX:+PrintGCTimeStamps 输出 gc
    -verbose:gc 输出 gc 信息

    因为方法区用于存放 Class 的相关信息，如类名、访问修饰符、字段描述，方法描述、常量池等，所以通过运行时生成大量的类去填满方法区.
    -XX:MaxMetaspaceSize=20m 指定元最大值
    -XX:MetaspaceSize=20m 指定元空间最小值

    jdk8 对应元空间内存溢出
    //java.lang.OutOfMemoryError: Metaspace

    当 jconsole 连接不上时，通过指定下列 JVM 参数连接
    -Dcom.sun.management.jmxremote
    -Dcom.sun.management.jmxremote.port=8011
    -Dcom.sun.management.jmxremote.ssl=false
    -Dcom.sun.management.jmxremote.authenticate=false
     */

    public static void main(String[] args) {

        while (true) {
            //通过 cglib 生成大量的类
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(Person.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
                return methodProxy.invoke(o, objects);
            });
            enhancer.create();
        }
    }

    private static class Person {
        public Person() {
        }
    }
}
