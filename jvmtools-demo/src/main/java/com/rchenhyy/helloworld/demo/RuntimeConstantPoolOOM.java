package com.rchenhyy.helloworld.demo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class RuntimeConstantPoolOOM {

    // 仅在JDK 1.6及以前版本中会出现永久代内存溢出
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
