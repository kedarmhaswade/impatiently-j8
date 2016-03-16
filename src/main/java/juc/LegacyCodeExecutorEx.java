package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/** An attempt to handle
 * <a href="http://stackoverflow.com/questions/35852606/java-thread-pool-how-to-stop-a-long-running-thread-immediately-using-shutdownno">
 *     this Stackoverflow thread :-) </a></a>
 * Created by kmhaswade on 3/7/16.
 */
public class LegacyCodeExecutorEx {
    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(2, new DaemonThreadFactory());

        executor.submit(new LegacySimulator());
        Thread.sleep(1000);
        executor.shutdownNow();
    }

    static class LegacySimulator implements Runnable {
        private final AtomicLong theLong;
        LegacySimulator() {
            theLong = new AtomicLong(1);
        }
        @Override
        public void run() {
            for (long i = 10; i < Long.MAX_VALUE; i++) {
                theLong.set(i*i);
            }
            System.out.println("Done!");
        }
    }
    static class DaemonThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setName("Daemon Thread");
            t.setDaemon(true);
            return t;
        }
    }
}
