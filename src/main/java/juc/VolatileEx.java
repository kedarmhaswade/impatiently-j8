package juc;

import static java.lang.Thread.currentThread;

/**
 * Created by kedar on 3/1/17.
 */
public class VolatileEx {
    private static volatile boolean running = false;
    public static void main(String[] args) {
        Runnable runnable = () -> {
            running = true;
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        t.start();
        while (! running) {
            try {
                System.out.println(currentThread().getName() + " sleeping ...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
