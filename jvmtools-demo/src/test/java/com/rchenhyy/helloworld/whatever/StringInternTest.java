package com.rchenhyy.helloworld.whatever;

import org.junit.Test;

/**
 * 测试String.intern()方法的效果
 */
public class StringInternTest {

    // JDK 1.6, 常量池在永久代
    // JDK 1.7, 常量池不在永久代
    @Test
    public void intern() {
        String s1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(s1.intern() == s1); // true (jdk 1.7) false (jdk 1.6)

        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2.intern() == s2); // false (jdk 1.7) false (jdk 1.6)
    }
}
