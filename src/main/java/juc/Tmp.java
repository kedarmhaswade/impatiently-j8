package juc;

/**
 * Created by kmhaswade on 9/4/16.
 */
public class Tmp {
    static volatile boolean in = false;
    public static void main(String[] args) {
        final Object monitor = new Object();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("thread is daemon? : " + Thread.currentThread().isDaemon());
                synchronized(monitor) {
                    in = true;
                    try {
                        monitor.wait();     //this releases monitor
                        Thread.sleep(8000); //No-op
                        System.out.println("Resumed in "+Thread.currentThread().getName());

                    }catch(InterruptedException ignore) {/**/}
                }

            }}).start();

        System.out.println("Ready!");
        while(!in); //spin lock / busy waiting
        System.out.println("Set!");
        synchronized(monitor) {
            System.out.println("Go!");
            monitor.notifyAll();
        }
    }
}
