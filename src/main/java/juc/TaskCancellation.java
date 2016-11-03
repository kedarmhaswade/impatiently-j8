package juc;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

import static java.math.BigInteger.ONE;

/**
 * <p>
 *     Both {@linkplain DirectWaitingThreadInterruption} and {@linkplain DirectSleepingThreadInterruption}
 *     demonstrate what happens when a thread that is being interrupted is either
 *     waiting or sleeping.
 * </p>
 * <p>
 *     But in modern Java applications, usually an {@linkplain ExecutorService}
 *     is used and <i>tasks</i> are submitted to it. The tasks themselves should have
 *     a cancellation policy.
 * </p>
 * Created by kedar on 10/26/16.
 */
public class TaskCancellation {

    static class PrimeProducerTask implements Runnable {
        private final BlockingQueue<BigInteger> bq;
        PrimeProducerTask(BlockingQueue<BigInteger> bq) {
            this.bq = bq;
        }
        @Override
        public void run() {
            BigInteger bi = BigInteger.ONE;
            while (! Thread.currentThread().isInterrupted()) {
                BigInteger nextProbablePrime = bi.nextProbablePrime();
                try {
                    bq.put(nextProbablePrime);
                } catch (InterruptedException e) {
                    // should I ignore this?
                }
                bi = nextProbablePrime;
            }
        }
    }
}
