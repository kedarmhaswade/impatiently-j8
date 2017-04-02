package juc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by kedar on 3/14/17.
 */
public class TimeoutAsyncTask {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> future = null;
        Random r = new Random();

        try {
            future = CompletableFuture.supplyAsync(() -> {
                try {
                    while(true) {
                        System.err.println("Inner");
                        Thread.sleep(1000);
                    }
//                    System.out.println("napping at work for 10 seconds :-)");
//                    Thread.sleep(10_000);
//                    return r.nextInt(100);
                } catch (InterruptedException e1) {
                    System.out.println(Thread.currentThread().getName() + " got an interrupted exception");
                    System.err.println(e1.getClass().getName() + " " + e1.getMessage());
                }
                return -1;
            });
            Integer result = future.get(3, TimeUnit.SECONDS);
            System.out.println("result after timeout: " + result);
        } catch (TimeoutException exp) {
            System.out.println(exp.getClass().getName() + " " + exp.getMessage());
            System.out.println(Thread.currentThread().getName());
            boolean canceled = future.cancel(true);
            System.out.println("canceled the future interrupting the task? " + canceled);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.err.println("Done");
        Thread.sleep(5000);
        System.err.println("Done Done");
    }
}
