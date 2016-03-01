package juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kmhaswade on 2/28/16.
 */
public class PrivateFieldInEnclosing {
    private long value;
    PrivateFieldInEnclosing() {}
    void execute() {
        value = initializeValue();
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(new Y());
    }

    class Y implements Runnable {
        @Override
        public void run() {
            System.out.println(value);
        }
    }

    private long initializeValue() {
        return 20;
    }

    public static void main(String[] args) {
        new PrivateFieldInEnclosing().execute();
    }
}
