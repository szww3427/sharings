package com.rchenhyy.helloworld.demo;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    /*
     * 由直接内存导致的内存溢出, 一个明显的特征是在Heap Dump文件中不会看见明显的异常.
     * 如果发现OOM之后Dump文件很小, 而程序中又直接或间接使用了NIO, 那就可以考虑检查一下是不是这方面的原因.
     */
    public static void main(String[] args) throws IllegalAccessException {
        Field theUnsafeField = Unsafe.class.getDeclaredFields()[0];
        theUnsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafeField.get(null);

        // 可能会导致电脑死机
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
