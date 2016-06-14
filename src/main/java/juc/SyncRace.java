package juc;

/** Trying to see how the thread dump looks for synchronized block
 * Created by kmhaswade on 4/22/16.
 */
public class SyncRace {
    private static class Lock {
        static synchronized public void sleep() {
            // simulate hoarding the lock, so we could take the thread dump
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(()->{Lock.sleep();}, "thread1").start();
        new Thread(()->{Lock.sleep();}, "thread2").start();
    }
}
