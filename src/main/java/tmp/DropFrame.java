package tmp;


/**
 * <p>
 *     This exercise is to demonstrate the use of the IDEA feature named "drop frame" which lets
 *     you go back in time, although not in an idempotent way.
 * </p>
 * Created by kedar on 3/16/17.
 */
public class DropFrame {

    public static void main(String[] args) {
        int nd = nd(123);
        System.out.println(nd);
    }

    private static int nd(int n) {
        // now put a breakpoint on the next line and suppose you were expecting v to be 1
        // but it will return 2 and you are puzzled. So, you want to enter inside m1 to see
        // what is going on and that's where drop-frame feature comes in handy.
        // One way to do this is with a recursive method call
        if (n < 10)
            return 1;
        return 1 + nd(n/10);
    }
}
