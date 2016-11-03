package practice;

/**
 * <p>
 *     Given a sorted array, how would you find if two of its elements add up to a given sum?
 * </p>
 * <p>
 *     Use invariants. The array is sorted!
 * </p>
 * Created by kmhaswade on 8/25/16.
 */
public class BasicTwoSum {

    /**
     * Returns a two dimensional array that are indexes of elements whose sum is given (sum). If no such elements
     * exist, returns an array [-1, -1]. Array must be sorted.
     * @param a the sorted array.
     * @param sum the desired sum
     * @return a 2-D array of indexes
     */
    static int[] get(int[] a, int sum) {
        int length = a.length;
        int i = 0, j = length - 1;
        while (i < j) {
            int f = a[i], s = a[j];
            int tmp = f + s;
            if (tmp == sum) {
                return new int[] {i, j};
            } else if (tmp > sum) { // current sum is more, reduce the far end
                j -= 1;
            } else { // current sum is less, increase the near end
                assert tmp < sum;
                i += 1;
            }
        }
        // no such sum
        return new int[] {-1, -1};
    }
}
