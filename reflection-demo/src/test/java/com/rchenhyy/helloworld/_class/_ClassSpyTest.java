package com.rchenhyy.helloworld._class;

import org.junit.Test;

/**
 * @author chenjiahua.chen
 * @version v1.0.0
 * @since 17/9/16
 */
public class _ClassSpyTest {

    @Test
    public void test() {
        _ClassSpy.main("java.lang.String", "CONSTRUCTOR", "FIELD");
    }

    @Test
    public void test2() {
        _ClassSpy.main("java.nio.channels.ReadableByteChannel", "METHOD");
    }

    @Test
    public void test3() {
        // In the fields portion of these results, enum constants are listed. While these are technically fields, it
        // might be useful to distinguish them from other fields. This example could be modified to use
        // java.lang.reflect.Field.isEnumConstant() for this purpose.

        // In the methods section of the output, observe that the method name includes the name of the declaring class.
        // Thus, the toString() method is implemented by Enum, not inherited from Object.
        _ClassSpy.main("com.rchenhyy.helloworld._class.ClassMember", "METHOD", "FIELD");
    }
}