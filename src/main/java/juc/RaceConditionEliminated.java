package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * <p>
 *     As {@link RaceCondition} demonstrates, updates using the ++ operator to a volatile variable
 *     are not atomic. This can be eliminated by using a concurrent data structure like
 *     {@link AtomicInteger}. Every task updates the shared variable exactly as many times as it
 *     is asked to (1000) and a given number (10) of such concurrently executing tasks together
 *     update the shared variable <i>exactly</i> an expected number (10*1000 = 10,000) of times.
 * </p>
 * <p>
 *     The final value of <code>countFastRecursive</code> is always <code>10000</code>.
 *     Note that {@link ExecutorService#awaitTermination(long, TimeUnit)} is required to ensure
 *     that the right value is printed by the <code>main</code> thread.
 * </p>
 */
public class RaceConditionEliminated {

    private static AtomicInteger count = new AtomicInteger();
    public static void main(String[] args) throws InterruptedException {
        // update the shared variable traditionally
        Runnable increment = () -> {
            System.out.println("countFastRecursive was: " + count.get() + " in thread: " + Thread.currentThread());
            for (int i = 0; i < 1000; i++)
                count.getAndIncrement();
            System.out.println("countFastRecursive updated to: " + count.get() + " in thread: " + Thread.currentThread());
        };
        // update the shared variable as a side effect of an IntConsumer#accept
        Runnable incrementStream = () -> {
            System.out.println("countFastRecursive was: " + count.get() + " in thread: " + Thread.currentThread());
            IntStream.rangeClosed(1, 1000).forEach(i -> count.getAndIncrement());
            System.out.println("countFastRecursive updated to: " + count.get() + " in thread: " + Thread.currentThread());
        };
        Runnable bulkUpdate = () -> count.getAndAdd(1000);
        ExecutorService exec = Executors.newCachedThreadPool(); // short lived tasks
        try {
            for (int i = 0; i < 10; i++) {
                exec.execute(incrementStream);
//                exec.execute(increment);
            }
        } finally {
            exec.shutdown();
            boolean done;
            while (! (done = exec.awaitTermination(1, TimeUnit.SECONDS))) {
                System.out.println("waited, but not long enough");
            }
            System.out.println("final: " + count);
        }
    }
}
