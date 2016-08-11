package practice;

/**
 * <p>
 *     There are several GCD algorithms and Wikipedia has them all. An interesting method is the so-called
 *     binary method where we use various reductions till we reach a case where both the numbers are the same.
 * </p>
 * Created by kmhaswade on 8/9/16.
 */
public class Gcd {

    /**
     * Implements the binary method; assumes a and b to be non-negative.
     * @param a
     * @param b
     * @return the GCD of given non-negative arguments.
     */
    static long binary(long a, long b) {
        int d = 0; // powers of 2 common to both
        // some initial validation
        while (a != b) {
            if (((a & 1) == 0) && ((b & 1) == 0)) { // both are even
                a >>>= 1;
                b >>>= 1;
                d += 1;
            } else if (((a & 1) == 0) && ((b & 1) == 1)) { // a is even, b odd
                a >>>= 1;
            } else if (((a & 1) == 1) && ((b & 1) == 0)) { // a is odd, b even
                b >>>= 1;
            } else { // both are odd
                long max = Math.max(a, b);
                long min = Math.min(a, b);
                a = (max - min) >>> 1;
                b = min;
            }
        }
        return a << d; // same as (a x 2^d)
    }
}
