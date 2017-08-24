package tmp;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by kmhaswade on 5/18/16.
 */
public class LatchAwaitDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(2);

        Thread t = new Thread(new Worker(startSignal, doneSignal, 0));
        t.start();
        Thread.sleep(20);
        t.interrupt();
        startSignal.countDown();      // let all threads proceed
        doneSignal.await(330, TimeUnit.SECONDS);           // wait for all to finish
    }
}

class Worker implements Runnable {
    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;
    private final int x;
    Worker(CountDownLatch startSignal, CountDownLatch doneSignal, final int x) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.x = x;
    }
    @Override
    public void run() {
        try {
            startSignal.await();
            doWork(); // may throw exception
        } catch (InterruptedException ex) {
            System.out.println(Thread.currentThread() + " is interrupted ...");
            doneSignal.countDown();
            // handle interruption ...
        } finally {
//            doneSignal.countDown(); // cause await to return asap
        }
    }

    void doWork() {
        System.out.println("going to result in Arithmetic exception ....");
        System.out.println((1/x));
    }
}