package juc;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <p>
 * The only way for a Java thread (A) to make Java thread (usually another, B) not continue
 * what it is doing is for A to <i>grab B's attention</i>. Of course, Java programming
 * language should provide means for the thread B to notice that some other thread
 * is trying to grab its attention. B may completely ignore A's <i>poking</i>.
 * This rather wishful attention grabbing of B by A is called <i>interruption.</i> The
 * interruption may or may not always succeed.
 * </p>
 * <p>
 * For instance, what if B is blocked on I/O? In such a case, A's pokes go
 * unnoticed by B. But there are two specific cases where A's pokes are at least brought
 * to B's attention:
 * <ul>
 * <li>B is <b>sleeping</b></li>
 * <li>B is <b>waiting</b></li>
 * </ul>
 * </p>
 * <p>
 * This program demonstrates interruption when the interrupted thread (B) is waiting
 * (in other words, it has previously called {@linkplain Object#wait() wait}, typically
 * for a condition to become true.
 * </p>
 * <p>
 * We are going to use the standard idiom of {@linkplain BlockingQueue a blocking queue}
 * waiting for the queue to have something before {@linkplain BlockingQueue#take() a call to take}
 * returns.
 * </p>
 * Created by kedar on 10/26/16.
 */
public class DirectWaitingThreadInterruption {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> emptyForEver = new ArrayBlockingQueue<>(1);
        Thread b = new InterruptionExceptionCatcher(emptyForEver);
        b.start();
        System.out.println("I am: " + Thread.currentThread().getName());
        System.out.println("I will endlessly attempt to interrupt B, maybe he'll stop what it's doing someday?");
        int attempts = 0;
        while (true) {
            b.interrupt();
            attempts += 1;
            if (b.isAlive()) {
                System.out.println("Attempt #" + attempts + " failed, B is still alive");
                System.out.println("is B interrupted? : " + b.isInterrupted());
                System.out.println("Taking some rest before next attempt ...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // squelching this exception is okay ...
                }
            } else {
                System.out.println("Successfully interrupted B, on Attempt# " + attempts +
                        " yay, now exiting gracefully");
                break;
            }
        }
        // now next
    }

    static class InterruptionExceptionCatcher extends Thread {
        private final BlockingQueue<Integer> q;

        public InterruptionExceptionCatcher(BlockingQueue q) {
            super("B");
            this.q = q;
        }

        @Override
        public void run() {
            Random r = new Random();
            while (true) {
                try {
                    Integer t = q.take(); // this calls Object#wait internally, if q is empty (and it is)
                } catch (InterruptedException e) {
                    System.out.println("Am I: " + Thread.currentThread().getName() + " in interrupted state? " + Thread.currentThread().isInterrupted());
                    System.out.println("Wait, what, someone interrupted me, i.e. the thread " + this.getName());
                    // this is where the control should come when someone interrupts this thread
                    // let's respect the interruption by returning from this method
                    // only if a random number generator tells me so
                    if (r.nextBoolean()) {
                        System.out.println("all right, God tells me to stop waiting on the q which I think is " +
                                "empty anyway: q.isEmpty() = " + q.isEmpty() + " and terminate myself :-(");
                        System.out.println(Thread.currentThread().getName() + " cleaning up before termination ...");
                        return;
                    } else {
                        System.out.println("No, God tells me not to pay attention to this " +
                                "interruption attempt :-D. I am going back to what I was doing, " +
                                "you can take a thread dump and see me in WAITING state");
                    }
                }
            }
        }
    }

}
