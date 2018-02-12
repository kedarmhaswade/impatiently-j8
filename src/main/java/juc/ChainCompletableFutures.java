package juc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Consider that you want to <i>chain</i> certain actions one after
 * another, but they should all be done asynchronously. This is really a
 * coordination of <code>CompletableFuture</code> instances:
 * <ul>
 *     <li>Ask A <code>Supplier</code> for a String holding int value</li>
 *     <li>Parse the String into an int</li>
 *     <li>Double the number</li>
 *     <li>Print it</li>
 * </ul>
 * Do it all asynchronously.
 * (See Modern Java Recipes for the example).
 */
public class ChainCompletableFutures {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> nothing = chainedFuture().thenAcceptAsync(x -> {
            System.out.println("This acceptance task is done asynchronously in this thread: " + Thread.currentThread());
            System.out.println(x);
        });
        System.out.println(Thread.currentThread() + " is long done, the async operation will be done later ...");
        System.out.println(nothing.get()); // block the main thread to prevent JVM from going down
        // or you could do nothing.join()
    }

    private static CompletableFuture<Integer> chainedFuture() {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("This supplier task is done asynchronously in this thread: " + Thread.currentThread());
                return "42";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "null";
            }
        });
        return cf.thenApply(ChainCompletableFutures::parse)
            .thenApply(ChainCompletableFutures::x2);
    }
    private static Integer x2(Integer n) {
        return n << 1;
    }

    private static Integer parse(String s) {
        try {
            Thread.sleep(20);
            System.out.println("This parsing task is done asynchronously in this thread: " + Thread.currentThread());
            return Integer.valueOf(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
