package com.rchenhyy.helloworld._members;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

public class _FieldSpy<T> {
    public boolean[][] b = { { false, false }, { true, true } };
    public String name = "Alice";
    public List<Integer> list;
    public T val;

    @Test
    public void test() {
        _FieldSpy.main("com.rchenhyy.helloworld._members._FieldSpy", "b");
        _FieldSpy.main("com.rchenhyy.helloworld._members._FieldSpy", "name");
        _FieldSpy.main("com.rchenhyy.helloworld._members._FieldSpy", "list");
        /**
         * The type for the field val is reported as java.lang.Object because generics are implemented via type erasure
         * which removes all information regarding generic types during compilation. Thus T is replaced by the upper
         * bound of the type variable, in this case, java.lang.Object.
         */
        _FieldSpy.main("com.rchenhyy.helloworld._members._FieldSpy", "val");
    }

    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);
            Field f = c.getField(args[1]);
            System.out.format("Type: %s%n", f.getType());
            /**
             * Field.getGenericType() will consult the Signature Attribute in the class file if it's present. If the
             * attribute isn't available, it falls back on Field.getType() which was not changed by the introduction of
             * generics. The other methods in reflection with name getGenericFoo for some value of Foo are implemented
             * similarly.
             */
            System.out.format("GenericType: %s%n", f.getGenericType());

            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException | NoSuchFieldException x) {
            x.printStackTrace();
        }
    }
}