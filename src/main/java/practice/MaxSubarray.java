package practice;

/**
 * <p>
 *     A subarray of an array is an array of its contiguous entries. The maximum subarray is that subarray the sum
 *     of whose elements is maximum. Given an array, find the value of its max subarray.
 * </p>
 * Created by kmhaswade on 8/12/16.
 */
public class MaxSubarray {
    /*
    This is a famous problem which could be solved using iterative improvement. The following implementation
    uses dynamic programming.
     */
    static long value(int[] a) {
        long value, max;
        max = value = a[0];
        int length = a.length;
        for (int i = 1; i < length; i++) {
            int e = a[i];
            long tmp = value + e;
            value = tmp > e ? tmp : e;
            max = Math.max(value, max);
        }
        return max;
    }
}
