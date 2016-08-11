package practice;

/**
 * <p>
 *     Given a set of coins (with certain values which may or may not repeat), find the smallest amount that can not
 *     be changed. For instance, the smallest amount that can not be changed with the set of coins with values
 *     &lt;1, 1, 2, 4, 10> is 9.
 * </p>
 * <p>
 *     Brute-force solution is quite wasteful.
 * </p>
 * Created by kmhaswade on 8/10/16.
 */
public class MinUnchangeableAmount {
    static long find(int[] a) {
        long minChangeable = 0;
        for (int v : a) {
            if (v > minChangeable + 1) {
                break;
            }
            minChangeable += v;
        }
        return minChangeable + 1;
    }
}
