package com.rchenhyy.helloworld.demo;

/**
 * VM Args: -Xss2m
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while (true)
            ;
    }

    public void stackLeakByThread() {
        while (true) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    } // Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread

}
