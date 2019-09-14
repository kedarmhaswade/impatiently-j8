package tmp;

import java.util.concurrent.CountDownLatch;

public class Reorder {
    private static int x = 1;

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startSignal, doneSignal;
        startSignal = new CountDownLatch(1);
        doneSignal = new CountDownLatch(2);
        new Thread(() -> {
            try {
                startSignal.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x += 2;
            doneSignal.countDown();
        }).start();
        new Thread(() -> {
            try {
                startSignal.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            x *= 2;
            doneSignal.countDown();
        }).start();
        startSignal.countDown();
        doneSignal.await();
        System.out.printf("x: %d\n", x);
    }
}
