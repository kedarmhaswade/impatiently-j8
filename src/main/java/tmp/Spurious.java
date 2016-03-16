package tmp;

/**
 * Never call wait() on a Thread object
 */
public class Spurious {
    public static void main(String[] args) {
//        final Object lock = new Object();
        Thread t1 = new Thread() {
            public void run() {
               // System.out.println("Hey!");
            }
        };
        Thread t2 = new Thread() {
            public void run()
            {
                try {
                    synchronized (t1) {
                        t1.wait();
                    }
                } catch (InterruptedException e) {
                    return;
                }
                System.out.println("Done.");
            }
        };
        t1.start();
        t2.start();
    }
}