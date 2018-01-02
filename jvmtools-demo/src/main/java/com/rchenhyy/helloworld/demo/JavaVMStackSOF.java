package com.rchenhyy.helloworld.demo;

/**
 * VM Args: -Xss160k
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackSOF stackSOF = new JavaVMStackSOF();
        try {
            stackSOF.stackLeak();
        } catch (Throwable t) {
            System.out.println("Stack length: " + stackSOF.stackLength);
            throw t;
        }
    }
    // Stack length: 771
    // Exception in thread "main" java.lang.StackOverflowError
}
