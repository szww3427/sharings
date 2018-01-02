package com.rchenhyy.helloworld._class;

import org.junit.Test;

/**
 * @author chenjiahua.chen
 * @version v1.0.0
 * @since 17/9/16
 */
public class _ClassDeclarationSpyTest {

    @Test
    public void test() {
        // public interface ConcurrentNavigableMap<K,V> extends ConcurrentMap<K,V>, NavigableMap<K,V>
        _ClassDeclarationSpy.main("java.util.concurrent.ConcurrentNavigableMap");
    }

    @Test
    public void test2() {
        // Since arrays are runtime objects, all of the type information is defined by the Java virtual machine.
        // Arrays implement Cloneable and java.io.Serializable and their direct superclass is always Object.
        _ClassDeclarationSpy.main("[Ljava.lang.String;");
    }

    @Test
    public void tes3() {
        _ClassDeclarationSpy.main("java.io.InterruptedIOException");
    }
}