package juc;

/**
 * <p>
 * An attempt to answer
 * <a href="http://stackoverflow.com/questions/36177926/java-multithreading-how-to-print-numbers-in-natural-order">
 * this question on SO</a>.
 * <p/>
 * </p>
 * The idea is to alternate between threads, using a low-level primitive like wait and notify.
 * Created by kmhaswade on 3/23/16.
 */
public class WaitNotifyDemoEvenOddThreads {
    /**
     * A transfer object, only use with proper client side locking!
     */
    static final class LastNumber {
        int num;
        final int limit;

        LastNumber(int num, int limit) {
            this.num = num;
            this.limit = limit;
        }
    }

    static final class NumberPrinter implements Runnable {
        private final LastNumber last;
        private final int init;

        NumberPrinter(LastNumber last, int init) {
            this.last = last;
            this.init = init;
        }

        @Override
        public void run() {
            int i = init;
            synchronized (last) {
                while (i <= last.limit) {
                    while (last.num != i) {
                        try {
                            last.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " prints: " + i);
                    last.num = i + 1;
                    i += 2;
                    last.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        LastNumber last = new LastNumber(0, 10);
        NumberPrinter odd = new NumberPrinter(last, 1);
        NumberPrinter even = new NumberPrinter(last, 0);
        new Thread(odd, "o").start();
        new Thread(even, "e").start();
    }
}
