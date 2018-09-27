package com.senlin.jvm.oom;

import com.senlin.commons.util.AssertUtils;

/**
 * String Intern 方法特性
 * @author gsl
 * @date 2018/6/10 11:27.
 */
public class StringIntern {

    public static void main(String[] args) {
        String s1 = "g-senlin";
        String s2 = "g-senlin";

        // 通过 new 指令生成字符对象
        String s3 = new String("g-senlin");
        String s4 = new String("g-senlin");

        AssertUtils.assertTrue(s1==s2);
        AssertUtils.assertFalse(s1==s3);

        //intern() 方法先判断常量池中是否已存在该字符串，存在则直接返回字符串所在引用地址，
        // 不存在则先将该字符串加入常量池再返回其在常量池中的引用地址.
        AssertUtils.assertTrue(s1==s3.intern());
        AssertUtils.assertFalse(s4==s3);
        AssertUtils.assertTrue(s4.intern()==s3.intern());

        String s5 = new StringBuilder("您好").append("java").toString();
        String s6 = new StringBuilder("ja").append("va").toString();

        //在 Hotspot 虚拟机 1.7 之后 String 的 intern()的实现不再是复制实例，只是在常量池中记录首次出现的实例的引用，
        // 因此 intern() 返回的引用指向实例就是 StringBuilder 创建的实例.
        AssertUtils.assertTrue(s5 == s5.intern());

        // 因 StringBuilder 生成的字符串 java 已经在常量池中存在，所以 intern() 方法返回的是常量池的中 java 字符串的引用地址，所以与StringBuilder 生成的 java 引用地址不同.
        AssertUtils.assertFalse(s6 == s6.intern());

    }
}


