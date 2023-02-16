package com.bomfim.finallyblock;

import java.io.Closeable;
import java.io.IOException;

public class FinallyBlock {

    public static void main(String[] args) {
        int x = 0;
        int y = 0;

        tryCatchFinally(x, y); //Console: Catch error / Finally block

        tryWithResources(); //Console: Closed / This is an error / Finally block
        tryFinally(x, y);   //Console: Finally block | Throw java.lang.ArithmeticException: / by zero
    }

    private static void tryWithResources() {

        try (MyClass myClass = new MyClass()) {
            myClass.throwError();
        } catch (Exception ex) {
            System.out.println("This is an exception");
        } catch (Error error) {
            System.out.println("This is an error");
        }
    }

    public static class MyClass implements Closeable {

        void throwError() {
            throw new IllegalAccessError("Error");
        }

        @Override
        public void close() throws IOException {
            System.out.println("Closed");
        }
    }

    private static void tryFinally(int x, int y) {
        try {
            System.out.println(x / y);
            System.out.println("After error");
        } finally {
            System.out.println("Finally block");
        }
    }

    private static void tryCatchFinally(int x, int y) {
        try {
            System.out.println(x / y);
            System.out.println("After error");
        } catch (Exception ex) {
            System.out.println("Catch error");
            return;
        } finally {
            System.out.println("Finally block");
        }
    }
}
