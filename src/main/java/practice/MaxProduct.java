package practice;

/**
 * <p>
 *     You are given an array a of integers. Find the <b> largest product </b>
 *     that can be made by multiplying all but one of the entries in a. Note: You can not use an entry more than once
 *     & you cannot use division operator for precision reasons (maybe this matters with floating point numbers, but
 *     what could be the reason to disallow division with integers?).
 * </p>
 * Created by kmhaswade on 8/10/16.
 */
public class MaxProduct {

    /**
     * Employs the O(n) time, O(n) additional space algorithm to calculate the products from left and right sides.
     * @param a the array of n integers
     * @return the maximum product of any (n-1) integers
     */
    static long find(int[] a) {
        int length = a.length;
        long[] lp = new long[length];
        lp[0] = 1;
        for (int i = 1; i < length; i++) {
            if (a[i - 1] == 0)
                break;
            lp[i] = lp[i - 1] * a[i - 1];
        }
        long[] rp = new long[length];
        rp[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            if (a[i + 1] == 0)
                break;
            rp[i] = rp[i + 1] * a[i + 1];
        }
        long max = Long.MIN_VALUE;
        for (int i = 0; i < length; i++) {
            max = Math.max(max, lp[i]* rp[i]);
        }
        return max;
    }
}
