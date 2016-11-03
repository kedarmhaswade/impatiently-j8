package juc;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * <p>
 *     See {@linkplain DirectWaitingThreadInterruption The Waiting Case}.
 *     This class demonstrates what happens when the interrupted thread (B) is sleeping.
 * </p>
 */
public class DirectSleepingThreadInterruption {
    public static void main(String[] args) throws InterruptedException {
        Thread b = new SleepingButInterruptionExceptionCatcher();
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

    static class SleepingButInterruptionExceptionCatcher extends Thread {

        public SleepingButInterruptionExceptionCatcher() {
            super("B");
        }

        @Override
        public void run() {
            Random r = new Random();
            while (true) {
                try {
                    Thread.sleep(10000); // long sleep
                } catch (InterruptedException e) {
                    System.out.println("Am I: " + Thread.currentThread().getName() + " in interrupted state? " + Thread.currentThread().isInterrupted());
                    System.out.println("Wait, what, someone interrupted me, i.e. the thread " + this.getName());
                    // this is where the control should come when someone interrupts this thread
                    // let's respect the interruption by returning from this method
                    // only if a random number generator tells me so
                    if (r.nextBoolean()) {
                        System.out.println("OK, God tells me to wake up!");
                        System.out.println(Thread.currentThread().getName() + " cleaning up before termination ...");
                        return;
                    } else {
                        System.out.println("No, God tells me to keep sleepin' in!");
                    }
                }
            }
        }
    }

}
