package com.rchenhyy.helloworld._members;

public class _MethodParameterExamples {

    public class InnerClass { }

    enum Colors {
        RED, WHITE;
    }

    public static void main(String... args) {
        System.out.println("InnerClass:");
        _MethodParameterSpy.printClassConstructors(InnerClass.class);

        System.out.println("enum Colors:");
        _MethodParameterSpy.printClassConstructors(Colors.class);
        _MethodParameterSpy.printClassMethods(Colors.class);
    }
}