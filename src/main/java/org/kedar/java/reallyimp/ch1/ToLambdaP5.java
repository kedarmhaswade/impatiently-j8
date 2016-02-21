package org.kedar.java.reallyimp.ch1;

/**
 * <p>
 *     Take a file from one of your projects that contains a number of ActionListener, Runnable, or the like.
 *     Replace them with lambda expressions.
 *     How many lines did it save? Was the code easier to read? Were you able to use method references?
 * </p>
 * Created by kmhaswade on 2/20/16.
 */
public class ToLambdaP5 {
    public static void main(String[] args) {
        demoRunnable();
    }

    private static void demoRunnable() {
        // creates a lambda expression for Runnable and starts a thread with that
        new Thread(() -> System.out.println(Thread.currentThread().getName()), "thread-λ").start();
        new Thread(ToLambdaP5::printThread, "thread-method-ref").start();
    }
    private static void printThread() {
        System.out.println(Thread.currentThread().getName());
    }
}
