package org.kedar.java.reallyimp.ch1;

/**
 * <p>
 *     Didn't you always hate it that you had to deal with checked exceptions in a Runnable?
 *     Write a method uncheck that catches all checked exceptions and turns them into unchecked exceptions
 *     Hint: Make the uncheck method return a Runnable that could be passed to a Thread.
 * </p>
 * Created by kmhaswade on 2/20/16.
 */
public class UncheckP6 {
    public static void main(String[] args) {
        new Thread(uncheck(() -> {
            System.out.println("ZZzz");
            Thread.sleep(1000);
        })).start();
    }

    private static Runnable uncheck(Computation c) {
        return () -> {
            try {
                c.doOrDie();
            } catch (Exception e) {
                System.err.println("no do, die: " + c);
                throw new RuntimeException(e);
            }
        };
    }
    @FunctionalInterface
    static interface Computation {
        // the only "functional method"
        void doOrDie() throws Exception;
    }
}
