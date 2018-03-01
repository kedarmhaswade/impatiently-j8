package juc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.LongAdder;

/**
 * An example program to understand the JUC classes and interfaces.
 * <p>
 * Find the total number of primes from 2 to a (rather large, but fitting in long) given limit.
 * </p>
 * <p> Of course, one can do this sequentially, but it would be fun to try out the
 * abstractions and interfaces in j.u.c. library.</p>
 */
public class PrimeCensus {

    public static void main(String[] args) {
        // some handling of the command line params is necessary, here we require three params
        long low = Long.parseLong(args[0]);
        long high = Long.parseLong(args[1]);
        int nConcurrentTasks = Integer.parseInt(args[2]);
        ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Long>> results;
        try {
            results = es.invokeAll(getPrimeCountingTasks(low, high, nConcurrentTasks));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return; // we have a problem that we need to fix first
        }
        LongAdder sum = new LongAdder();
        for (Future<Long> result : results) {
            try {
                long primesInRange = result.get();
                sum.add(primesInRange);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        es.shutdown();
        System.out.println("total number of primes: " + sum.sum());
        // note that there are no concurrent updates while this call sum.sum() is made, so we're safe.
    }

    private static List<PrimeCounter> getPrimeCountingTasks(long low, long high, int nConcurrentTasks) {
        if (nConcurrentTasks < 2) {
            nConcurrentTasks = 1; // we insist on at least one last, perhaps we could throw IAE here
        }
        if (low < 2) {
            throw new IllegalArgumentException("provide a number >= 2");
        }
        if (high < low) {
            throw new IllegalArgumentException("invalid, high: " + high + " < " + low);
        }
        List<PrimeCounter> tasks = new ArrayList<>(nConcurrentTasks);
        int size = (int) ((high + 1 - low) / nConcurrentTasks);
        for (int i = 0; i < nConcurrentTasks - 1; i++) {
            long high1 = low + size - 1;
            PrimeCounter task = new PrimeCounter(low, high1);
            tasks.add(task);
            System.out.println("added: " + task);
            low = high1 + 1;
        }
        PrimeCounter last = new PrimeCounter(low, high);
        tasks.add(last); // remaining go to the last range
        System.out.println("added: " + last);
        return tasks;
    }

    private static final class PrimeCounter implements Callable<Long> {
        private final long low;
        private final long high;

        PrimeCounter(long low, long high) {
            this.low = low;
            this.high = high;
        }

        private long count() {
            long c = 0L;
            long from = low - 1;
            BigInteger t = BigInteger.valueOf(from);
            while (true) {
                long pp = t.nextProbablePrime().longValue();
                if (pp > high) {
                    break;
                }
                c += 1;
//                System.out.println(pp);
                t = BigInteger.valueOf(pp);
            }
            System.out.println("number of primes in range: [" + this.low + ", " + this.high + "]: " + c);
            return c;
        }

        @Override
        public Long call() {
            return count();
        }
        @Override
        public String toString() {
            return "PrimeCounter [" + low + ", " + high + "]";
        }
    }
}
