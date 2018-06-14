package juc;

import static java.lang.Thread.sleep;

/**
 * <p>
 *     One of the many things we must not do while trying to write thread-safe programs is let
 *     the <code>this</code> reference escape from a constructor.
 * </p>
 * <p>
 *     As JCiP says eloquently, <code>this</code> may escape when we attempt to start a thread whose
 *     {@link Runnable} is an instance of an inner class.
 * </p>
 * <p>
 *     But can it be reliably demonstrated? This program is an attempt to do so. Perhaps it can
 *     be demonstrated, when we use the low-level constructs like {@link Thread}.
 * </p>
 * Created by kedar on 2/28/17.
 */
public class ThisEscape {
    private final int num;

    private ThisEscape() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Object.hashCode = " + ThisEscape.this.hashCode() + " value of final field num: " + num);
            }
        });
        t.start();
        this.num = 42;
    }

    public static void main(String[] args) throws InterruptedException {
        ThisEscape e = new ThisEscape();
        System.out.println("Object.hashCode = " + e.hashCode() + " value of final field num: " + e.num);
        System.out.println("run it multiple times, you may see two different values of a 'final' field");
    }
}
