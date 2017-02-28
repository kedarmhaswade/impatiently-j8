package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * <p>
 *     Race condition occurs when a shared variable is updated by various tasks running
 *     concurrently and the value of that shared variable depends on which task (thread) wins the "race"
 *     to update the variable.
 * </p>
 * <p>
 *     Does this happen in practice? YES!
 * </p>
 * <p>
 *     Consider a variable <code>count</code> that is shared among 10 concurrent tasks, each of which
 *     increments it by 1000. If the initial value of count is 0, after all the tasks complete, it
 *     is fair that the final value of count is 0 + 10 * 1000 = 10,000. But does that happen?
 * </p>
 * Created by kedar on 2/24/17.
 */
public class RaceCondition {

    private static volatile int count = 0;
    public static void main(String[] args) throws InterruptedException {
        // update the shared variable traditionally
        Runnable increment = () -> {
            System.out.println("count was: " + count + " in thread: " + Thread.currentThread());
            for (int i = 0; i < 1000; i++)
                count++;
            System.out.println("count updated to: " + count + " in thread: " + Thread.currentThread());
        };
        // update the shared variable as a side effect of an IntConsumer#accept
        Runnable incrementStream = () -> {
            System.out.println("count was: " + count + " in thread: " + Thread.currentThread());
            IntStream.rangeClosed(1, 1000).forEach(i -> count++);
            System.out.println("count updated to: " + count + " in thread: " + Thread.currentThread());
        };
        Runnable bulkUpdate = () -> count += 1000;
        ExecutorService exec = Executors.newCachedThreadPool(); // short lived tasks
        try {
            for (int i = 0; i < 10; i++) {
//                exec.execute(incrementStream);
                exec.execute(increment);
            }
        } finally {
            exec.awaitTermination(5, TimeUnit.SECONDS);
            exec.shutdown();
            System.out.println("final: " + count);
        }
    }
}
