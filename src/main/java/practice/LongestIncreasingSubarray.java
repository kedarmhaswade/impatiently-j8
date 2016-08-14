package practice;

/**
 * <p>
 *     Implement an algorithm that takes as input an array a of n elements and returns the beginning and
 *     ending indices of a longest increasing subarray (contiguous) of a.
 * </p>
 * Created by kmhaswade on 8/13/16.
 */
public class LongestIncreasingSubarray {
    /**
     * Returns the first indexes of the longest increasing subarray as a 2-element array.
     * @param a the array of integers
     * @return
     */
    static int[] indexesFirst(int[] a) {
        int longest = -1;
        int streak = 1;
        int fi = 0, li = 0;
        int maxfi = 0, maxli = 0;
        int length = a.length;
        for (int i = 1; i < length; i++) { // consider sub-array ending at i
            if (a[i] > a[i - 1]) { // streak extends
                streak += 1;
                li += 1;
            } else { // new streak starts
                streak = 1;
                fi = i;
                li = fi;
            }
            if (streak > longest) {
                longest = streak;
                maxfi = fi;
                maxli = li;
            }
        }
        return new int[]{maxfi, maxli};
    }
    // the above implementation misses a heuristic that improves the best case complexity
}
