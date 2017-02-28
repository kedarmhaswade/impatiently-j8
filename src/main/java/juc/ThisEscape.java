package juc;

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
    public final int pub;
    private final int pri;

    ThisEscape() {
        this.pri = 102;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(ThisEscape.this.hashCode() + " pub: " + pub);
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted while joining thread: " + t);
        }
        this.pub = 42;
    }

    public static void main(String[] args) {
        ThisEscape e = new  ThisEscape();
        System.out.println(e.hashCode() + " pub: " + e.pub);
        System.out.println("Because it was not fully constructed and the this reference escaped, you saw two different values of its 'final' field");
    }
}
