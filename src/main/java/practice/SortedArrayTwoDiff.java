package practice;

/**
 * <p>
 *     Given a <b> sorted </b> array, find two elements which differ by given difference.
 * </p>
 * <p>
 *     This is a tricky problem. The array's sortedness can be taken into account and for each element,
 *     one can use binary search to find another number that yields the given difference. This gives an O(n.logn)
 *     worst case running time. But we can do better if we start from the first two elements and then increment
 *     the indexes cleverly.
 * </p>
 * Created by kmhaswade on 8/28/16.
 */
public class SortedArrayTwoDiff {
    /**
     * Returns true if difference between any two elements of the array is d.
     * @param a the array
     * @param d the difference
     * @param indexes indexes of the elements that are d apart, should be a two-element array that this method mutates
     * @return true if such a pair exists, false otherwise
     */
    static boolean isAnyDiff(int[] a, int d, int[] indexes) {
        int i = 0, j = 1, length = a.length;
        while (j < length) {
            int tmp = a[j] - a[i];
            if (tmp == d) {
                indexes[0] = i;
                indexes[1] = j;
                return true;
            } else if (tmp < d) {
                j += 1;
            } else {
                assert tmp > d;
                if (i == j)
                    j += 1;
                else
                    i += 1;
            }
        }
        return false;
    }
}
