package tmp;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *     Given an array of integers, find the largest interval [a, b] (say a &lt; b)
 *     such that if a and b are contained in the array, all the integers between a and b
 *     (i.e. a + 1, a + 2, ..., b - 2, b - 1) are also contained in the array.
 * </p>
 * <p>
 *     This is an interesting algorithm that uses an additional hash table (O(n) memory).
 *     Without additional memory, one would need to sort the array and then examine each
 *     subarray.
 * </p>
 * Created by kedar on 10/25/16.
 */
public class LongestContainedInterval {

    /**
     * Given an array, finds <i>a</i> longest interval that is completely contained in it.
     * @param a the array of integers
     * @return a 2-element 1-dimensional array denoting the min and max of the interval. The
     * interval length then becomes (max - min).
     */
    static int[] find(int[] a) {
        if (a.length == 0)
            throw new IllegalArgumentException("empty array");
        if (a.length == 1)
            return new int[] {a[0], a[0]}; // cache?
        Set<Integer> set = Arrays.stream(a)
                .boxed()
                .collect(Collectors.toSet());
        int min = -1, max = -1;
        int iLen = 0;
        for (int x : a) {
            if (set.isEmpty())
                break;
            int minin = x - 1;
            while (! set.isEmpty() && set.contains(minin)) {
                set.remove(minin);
                minin -= 1;
            }
            minin += 1;
            int maxin = x + 1;
            while (! set.isEmpty() && set.contains(maxin)) {
                set.remove(maxin);
                maxin += 1;
            }
            maxin -= 1;
            set.remove(x);
            int diff = maxin - minin + 1;
            if (diff > iLen) {
                iLen = diff;
                max = maxin;
                min = minin;
            }
        }
        return new int[] {min, max};
    }
}
