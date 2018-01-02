package com.rchenhyy.helloworld._members;

import org.junit.Test;

/**
 * @author chenjiahua.chen
 * @version v1.0.0
 * @since 17/9/16
 */
public class _MethodSpyTest {
    @Test
    public void main() throws Exception {
        /**
         * public Constructor<T> getConstructor(Class<?>... parameterTypes) throws NoSuchMethodException,
         * SecurityException
         */
        _MethodSpy.main("java.lang.Class", "getConstructor");
        /**
         * The generic return type for the method Class.cast() is reported as java.lang.Object because generics are
         * implemented via type erasure which removes all information regarding generic types during compilation. The
         * erasure of T is defined by the declaration of Class:
         * 
         *  public final class Class<T> implements ...
         *
         * Thus T is replaced by the upper bound of the type variable, in this case, java.lang.Object.
         */
        _MethodSpy.main("java.lang.Class", "cast");
    }

}