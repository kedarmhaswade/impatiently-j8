package juc;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static java.lang.System.currentTimeMillis;
import static java.math.BigInteger.probablePrime;

/**
 * Demonstrates the use of {@linkplain CompletableFuture#whenComplete(BiConsumer)} method. This method
 * is used to handle both the cases when a CompletableFuture can complete in two ways: normally and exceptionally.
 *
 */
public class AsyncWhenComplete {
    public static void main(String[] args) {
        CompletableFuture<Long> asyncOperationWithResult =
        CompletableFuture.supplyAsync(() -> {
            // this is a supplier function that returns a Long
            Random rand = new Random(currentTimeMillis());
            boolean shouldThrow = rand.nextBoolean();
            System.out.println("shouldThrow: " + shouldThrow);
            if (shouldThrow) {
                RuntimeException e = new RuntimeException("simulated exception ...");
                System.out.println("exception message: " + e.getMessage());
                throw e;
            } else {
                BigInteger bi = probablePrime(1000, rand);
                long val = bi.longValue();
                System.out.println("Completing normally with the asyncOperationWithResult: " + val);
                return val;
            }
        });
        asyncOperationWithResult.whenComplete((asyncResult, e) -> {
            if (asyncResult == null) {
                System.out.println("async asyncOperationWithResult is null " +
                    "ensure that async operation threw exception with this message: " + e.getMessage());
            } else {
                assert e == null;
                System.out.println("previous async operation returned a asyncOperationWithResult: " + asyncResult);
            }
        });
    }
}
