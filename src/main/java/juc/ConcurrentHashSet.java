package juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

/**
 * <p>
 *     Somewhat interestingly, the JDK has a hidden way to use a concurrent hash set. That way goes
 *     through the {@link java.util.concurrent.ConcurrentHashMap}.
 * </p>
 * <p>
 *     This program uses multiple concurrent tasks to add certain number of elements to a set.
 *     After all those tasks are done, the number of elements in the set should match the sum of
 *     number of elements added to the set by each task. To avoid conflicts, each task adds
 * </p>
 * Created by kedar on 3/9/17.
 */
public class ConcurrentHashSet {
    private static final class AddTask implements Callable<Integer> {
        private final int low;
        private final int high;
        private final Set<Integer> set;
        AddTask(int low, int high, Set<Integer> set) {
            this.low = low;
            this.high = high;
            this.set = set;
        }
        @Override
        public Integer call() throws Exception {
            int expectedAdds = 10;
            for (int i = 0; i < expectedAdds; i++) {
                int e = low + i; // add low, low + 1, ..., low + expectedAdds
//                System.out.println("element: " + e);
                boolean added = set.add(e);
                assert added : "hmm! element already exists: " + e;
            }
            return expectedAdds;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Set<Integer> cs = ConcurrentHashMap.newKeySet(100); // this set is a concurrent data structure
        int nTasks = 10;
        List<Callable<Integer>> tasks = new ArrayList<>(nTasks);
        for (int i = 0; i < nTasks; i++) {
            tasks.add(new AddTask(i * 100, (i + 1) * 100, cs));
            //returned value ignored
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<Integer>> futures = exec.invokeAll(tasks);
        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.SECONDS);
        // hopefully the executor is shut down by now
        if (exec.isShutdown()) {
            System.out.println("set.size = " + cs.size());
            int elementsAdded = futures.stream().mapToInt(f -> {
                try {
                    return f.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return 0;
                }
            }).sum();
            System.out.println("num elements added by tasks = " + elementsAdded);
        } else {
            System.out.println("Giving up, the executor is still not shut down ...");
        }

    }
}
