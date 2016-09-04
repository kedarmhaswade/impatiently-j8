package juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by kmhaswade on 9/3/16.
 */
public class ThreadLocalEx {
    // Atomic integer containing the next thread ID to be assigned
    private static final AtomicInteger nextId = new AtomicInteger(0);

    // Thread local variable containing each thread's ID
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }
            };

    // Returns the current thread's unique ID, assigning it if necessary
    public static int get() {
        return threadId.get();
    }

    private static class Printer implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.printf("My Id: %s, and %d is my thread local variable%n",
                        Thread.currentThread().getName(), threadId.get());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " was interrupted, it's exiting now ...");
                }
            }
        }
    }

    public static void main(String[] args) {
        // use latch
        new Thread(new Printer()).start();
        new Thread(new Printer()).start();
        new Thread(new Printer()).start();
        new Thread(new Printer()).start();
    }
}