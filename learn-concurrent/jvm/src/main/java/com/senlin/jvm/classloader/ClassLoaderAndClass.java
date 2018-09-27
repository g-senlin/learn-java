package com.senlin.jvm.classloader;

import com.senlin.commons.util.AssertUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * 验证 不同类加载器加载的同一个 Class 文件生成的 Class 对象不想等.
 *
 * @author gsl
 * @date 2018/7/15 16:34.
 */
public class ClassLoaderAndClass {

    /*
    对于任意一个类，都需要由加载它的类加载器和这个类本身一同确立其在 Java 虚拟机中的唯一性，每一个类加载器，都拥有一个独立的类名称空间。
    即如果两个类来源于同一个 Class 文件，被同一个虚拟机加载，只要加载它们的类加载器不同，那么这两个类加载器就必定不想等。
    这里的相等指类的 Class 对象的 instanceof、isAssignableFrom、equals 方法返回true。

     */

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader myClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String classFileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                try (InputStream in = this.getClass().getResourceAsStream(classFileName)) {
                    if (in == null) {
                        return super.loadClass(name);
                    }
                    byte[] bytes = new byte[in.available()];
                    in.read(bytes);
                    return defineClass(name, bytes, 0, bytes.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(e.getMessage());
                }
            }
        };

        Class<?> clazz = myClassLoader.loadClass("com.senlin.jvm.classloader.ClassLoaderAndClass");

        System.out.println("class----" + clazz);
        //class----class com.senlin.jvm.classloader.ClassLoaderAndClass

        System.out.println("classloader----" + clazz.getClassLoader().getClass());
        // classloader----class com.senlin.jvm.classloader.ClassLoaderAndClass$1

        AssertUtils.assertFalse((clazz.equals(ClassLoaderAndClass.class)));
        AssertUtils.assertFalse((clazz.isAssignableFrom(ClassLoaderAndClass.class)));
        Object clazzObject = (Object) clazz;
        AssertUtils.assertFalse(clazzObject instanceof ClassLoaderAndClass);

    }
}
