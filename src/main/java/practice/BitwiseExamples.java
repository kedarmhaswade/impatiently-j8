package practice;

/**
 * <p>
 *     A few examples of bitwise trickery. HD is the ultimate reference, and this kind of trickery is mainly for
 *     compiler writers, but perhaps it is good to be familiar with how bitwise magic works.
 * </p>

 * Created by kmhaswade on 7/22/16.
 */
public class BitwiseExamples {
    /**
     * <p>
     *     Find the number of 1's (<code>population count</code>) in the binary representation of an
     *     integer (long) x. The JDK API {@linkplain Long#bitCount(long)}
     *     gives us this number, but this class uses following observation to get it:
     *     <ol>
     *         <li> x & ~(x-1) gives a number m that has a single 1 bit at the same location as the rightmost 1 bit in
     *         x. The number m has all other bits zero.</li>
     *         <li> x XOR m should unset this rightmost 1 bit in x.</li>
     *         <li> repeat 1, 2 till we get 0.</li>
     *     </ol>
     *     This is much faster than the method that shifts x one bit at a time n times, where n is the word-length.
     * </p>
     * @param x an integer
     * @return number of set bits in x's two's complement notation
     */
    public static int popCount(long x) {
        int pc = 0; // the number of 1 bits in x, aka population count
        while (x != 0) {
            x = x ^ (x & ~(x-1)); // find and unset the rightmost 1 bit in x
            pc += 1;
        }
        return pc;
    }

    /**
     * <p>
     *     We want to convert a number like (0101011000) into (0101011111).
     * </p>
     * <p>
     *     One idea is to find the rightmost 1 bit, isolate it and repeated bitwise-OR the given number with
     *     increasing power of 2.
     * </p>
     * <p>
     *     A faster way is to isolate the rightmost 1 and bitwise-OR the given number with that power of 2 - 1.
     *     A number that is (positive power of 2) - 1 has all bits 1. This is true of positive numbers.
     * </p>
     * @param x long value
     * @return see description
     */
    public static long rightPropagateRightmostOne(long x) {
//        return x | ((x & ~(x-1)) - 1) ;
        return x | (x-1);
    }
    /**
     * <p>
     *     Is the given number a power of two?
     * </p>
     * The idea is based on the observation that the (positive) power of 2 has only one bit (other than the sign bit)
     * set. Use the identity <code> x&~(x-1)</code> which yields a power of 2 (or Long.MIN_VALUE).
     * .MIN_VALUE)
     */
    public static boolean isPowerOf2(long x) {
        return x > 0 && x == (x & ~(x-1));
    }
}
