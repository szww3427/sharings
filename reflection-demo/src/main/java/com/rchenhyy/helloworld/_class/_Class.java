package com.rchenhyy.helloworld._class;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author chenjiahua.chen
 * @version v1.0.0
 * @since 17/9/10
 */
public class _Class {

    // ================================================================
    // Retrieving Class Objects
    // ================================================================

    @Test
    public void _getClass() {
        // Since arrays are Objects, it is also possible to invoke getClass() on an instance of an array.
        // The returned Class corresponds to an array with component type byte.
        int[] ints = new int[] {};
        Class<? extends int[]> aClass = ints.getClass();
        System.out.println(aClass); // class [I

        int[][] intss = new int[][] {};
        Class<? extends int[][]> bClass = intss.getClass();
        System.out.println(bClass); // class [[I
    }

    @Test
    public void _class() {
        Class c = int[][][].class;
        System.out.println(c); // class [[[I
    }

    @Test
    public void _forName() throws ClassNotFoundException {
        Class<?> cDoubleArray = Class.forName("[D");
        System.out.println(cDoubleArray);
        System.out.println(cDoubleArray == double[].class);

        Class<?> cStringArray = Class.forName("[[Ljava.lang.String;");
        System.out.println(cStringArray);
    }

    @Test
    public void _type() {
        // Each of the primitive types and void has a wrapper class in java.lang that is used for boxing of primitive
        // types to reference types.
        Class<Void> type = Void.TYPE;
        System.out.println(type);
        System.out.println(type == void.class);
    }

    // Methods that Return Classes

    @Test
    public void _superclass() {
        Class<?> superclass = String[].class.getSuperclass();
        System.out.println(superclass);
    }

    @Test
    public void _classes() {
        // class java.lang.Character$UnicodeScript
        // class java.lang.Character$UnicodeBlock
        // class java.lang.Character$Subset
        Class<?>[] classes = Character.class.getClasses();
        for (Class<?> aClass : classes) {
            System.out.println(aClass);
        }
    }

    @Test
    public void _declaredClasses() {
        // class java.lang.Character$CharacterCache
        // class ...
        Class<?>[] classes = Character.class.getDeclaredClasses();
        for (Class<?> aClass : classes) {
            System.out.println(aClass);
        }
    }

    @Test
    public void _declaringClass() throws NoSuchFieldException, NoSuchMethodException {
        // Class.getDeclaringClass()
        Class<?> declaringClass = System.class.getDeclaringClass();
        System.out.println(declaringClass); // null

        // Member.getDeclaringClass()
        Field field = System.class.getField("out");
        Class<?> fieldDeclaringClass = field.getDeclaringClass();
        System.out.println(field);
        System.out.println(fieldDeclaringClass); // System.class

        Method method = System.class.getMethod("getProperty", String.class);
        Class<?> methodDeclaringClass = method.getDeclaringClass();
        System.out.println(method);
        System.out.println(methodDeclaringClass); // System.class
    }

    @Test
    public void _enclosingClass() {
        Class<Thread.State> stateClass = Thread.State.class;
        System.out.println(stateClass);
        System.out.println(stateClass.getEnclosingClass());
        System.out.println(stateClass.getEnclosingClass() == stateClass.getDeclaringClass());

        // anonymous class
        Object o = new Object() {
            @Override
            public String toString() {
                return "MyObject";
            }
        };
        System.out.println(o.getClass()); // com.rchenhyy.helloworld._class._Class$1
        System.out.println(o.getClass().getEnclosingClass()); // com.rchenhyy.helloworld._class._Class
        System.out.println(o.getClass().getDeclaringClass()); // null
    }


    // ================================================================
    // Examining Class Modifiers and Types
    // ================================================================

}
