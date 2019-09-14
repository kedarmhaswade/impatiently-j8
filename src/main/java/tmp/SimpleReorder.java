package tmp;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class SimpleReorder {
    private static int x = 1;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> x += 2).start();
        new Thread(() -> x *= 2).start();
        MILLISECONDS.sleep(10);
        System.out.printf("x: %d\n", x);
    }
}
