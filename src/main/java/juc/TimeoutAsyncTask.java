package juc;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by kedar on 3/14/17.
 */
public class TimeoutAsyncTask {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> future = null;
        final Random r = new Random();

        try {
            future = CompletableFuture.supplyAsync(() -> {
                int retVal = BigInteger.probablePrime(1000, r).intValue();
                System.out.println("working async in this thread: " + Thread.currentThread().getName());
                System.out.println("The async operation in this stage is returning (which you can use in next stage): " + retVal);
                return retVal;
            });
//            System.out.println("result after timeout: " + result);
            future.thenAccept(result -> {
                System.out.println("Working async in this thread: " + Thread.currentThread().getName());
                System.out.println("got the result from previous stage: " + result);
            });
            future.get(3, TimeUnit.SECONDS); // blocking for the sake testing ...
            System.out.println("And this is the thread: " + Thread.currentThread().getName());
        } catch (TimeoutException exp) {
            System.out.println(exp.getClass().getName() + " " + exp.getMessage());
            System.out.println(Thread.currentThread().getName());
            boolean canceled = future.cancel(true);
            System.out.println("canceled the future interrupting the task? " + canceled);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
