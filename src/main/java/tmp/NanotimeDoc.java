package tmp;

public class NanotimeDoc {
    // System.nanoTime javadoc is buggy as of Java 8?

    public static void main(String[] args) throws InterruptedException {
        long timeoutNano = 200; //ns
        long t0 = System.nanoTime();
//        Thread.sleep(1);
        if (System.nanoTime() - t0 > timeoutNano) {
            System.out.println("time's up!");
        } else {
            System.out.println("there's still time!");
        }
        // fixed in 1.9!
//        long t0 = -100L;
//        long t1 = Long.MAX_VALUE - 50L;
//        System.out.println(t1 > t0);
//        System.out.println(t1 < t0);
//        long diff = Math.subtractExact(t1, t0);
//        System.out.println(diff);
    }
}
