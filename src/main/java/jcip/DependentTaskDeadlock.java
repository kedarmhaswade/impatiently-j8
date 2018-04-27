package jcip;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>Based on the listing 8.1 from JCiP. This program will deadlock by making a single
 * character change!</p>
 */
public class DependentTaskDeadlock {
    private static final Random rand = new Random();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(1);
        Future<Integer> future = exec.submit(new DependentTask(exec));
        Integer val = future.get();
        System.out.println("in: " + Thread.currentThread().getName() + " got: " + val);
        exec.shutdown();
    }

    static final class DependentTask implements Callable<Integer> {
        private final ExecutorService exec;

        DependentTask(ExecutorService exec) {
            this.exec = exec;
        }

        @Override
        public Integer call() throws Exception {
            Future<Integer> x = exec.submit(() -> rand.nextInt(1000));
            Integer dep = x.get();
            System.out.println("in: " + Thread.currentThread().getName() +  " the other task returned: " + dep);
            return dep + 10;
        }
    }
}
