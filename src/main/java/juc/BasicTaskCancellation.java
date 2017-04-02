package juc;

import java.util.concurrent.*;

/**
 * <p>
 * Examines what happens when a thread that is waiting for certain condition to be true with
 * a {@linkplain java.util.concurrent.BlockingQueue}.
 * </p>
 * Created by kedar on 3/11/17.
 */
public class BasicTaskCancellation {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> q = new ArrayBlockingQueue<>(1);
        Callable<Integer> blocked = new BlockedForever(q);
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<Integer> produced = exec.submit(blocked);
        Thread.sleep(1000);
        // after a second, wake up and try to cancel the task if it is not done
        if (! produced.isDone()) {
            boolean cancel = produced.cancel(true);
            System.out.println(Thread.currentThread().getName() + " cancelled the task? " + cancel);
        } else {
            try {
                System.out.println(produced.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.SECONDS);
//        exec.shutdownNow();  // returned value ignored
    }

    private static class BlockedForever implements Callable<Integer> {
        private final BlockingQueue<Integer> q;

        BlockedForever(BlockingQueue<Integer> q) {
            this.q = q;
        }

        @Override
        public Integer call() throws Exception {
//            return q.take();
            try {
                return q.take(); // will block forever!
            } catch (InterruptedException ie) {
                System.out.println("current thread: " + Thread.currentThread().getName() + " interrupt status: " + Thread.currentThread().isInterrupted());
                System.out.println("task was canceled, I am returning a 0");
                return 0;
            }
        }
    }
}
