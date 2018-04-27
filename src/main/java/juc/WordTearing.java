package juc;

public final class WordTearing extends Thread {
    private static final int LENGTH = 8;
    private static final int ITERS = 1000000;
    private static byte[] counts = new byte[LENGTH]; // init to 0
    private static Thread[] threads = new Thread[LENGTH];
    private final int id;

    private WordTearing(int i) {
        id = i;
    }

    public static void main(String[] args) {
        for (int i = 0; i < LENGTH; ++i) {
            (threads[i] = new WordTearing(i)).start();
        }
    }

    public void run() {
        byte v = 0;
        for (int i = 0; i < ITERS; i++) {
            byte v2 = counts[id];  // read
            if (v != v2) {
                System.err.println("Word-Tearing found: " +
                    "counts[" + id + "] = " + v2 + ", should be " + v);
                return;
            }
            v++;
            counts[id] = v; // write
        }
    }
}

