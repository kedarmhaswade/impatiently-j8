package juc;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by kedar on 3/14/17.
 */
public class TimeoutFuture {
    public static void main(String[] args) {
        Random r = new Random();
        Callable<Integer> sleepingIdFetcher = () -> {
            try {
                while(true) {
                    System.err.println("Inner");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ie) {
                System.out.println("Uh oh, someone tried to wake me up ... " + Thread.currentThread().getName());
                System.out.println("Id Fetching task receives interruption in: " + Thread.currentThread().getName());
            }
            return -1;
        };
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<Integer> id = exec.submit(sleepingIdFetcher);
        try {
            id.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Ugh, who interrupted " + Thread.currentThread().getName() + " ?");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("alright, the task got timed out and I got the timeout exception: " + Thread.currentThread().getName());
            id.cancel(true);
            System.out.println("all threads should stop NOW");
        }
    }
}
