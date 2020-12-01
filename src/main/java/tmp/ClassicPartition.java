package tmp;

import java.util.Random;

/**
 * Implements the classic partitioning routine. Chooses a pivot and partitions the given
 * array of integers around that.
 */
public class ClassicPartition {

    /**
     * Partitions the given array in place in O(n) time. Chooses a random pivot which is placed at the 0th index.
     * The invariant maintained is that after every number (starting from index 1) is read, the pivot could
     * be placed at the index that is its correctly sorted position in the partial subarray examined so far.
     *
     * @param a an integer array that is partitioned in place
     * @return integer representing the index of the pivot; if this method returns a number i, then the following holds:
     * <pre>
     *     a[j] < pivot for all 0 <= j < i, and
     *     a[j] >= pivot for all i+1 <= j < a.length
     * </pre>
     */
    static int partition(int[] a) {
        setPivot(a);
        int pivot = a[0];
//        System.out.println("pivot: " + pivot);
        int pi = 0; //pi is the sorted index of the pivot
        for (int i = 1; i < a.length; i++) {
            if (a[i] < pivot) {
                a[pi] = a[i];
                a[i] = a[pi + 1];
                pi += 1;
            }
        }
        a[pi] = pivot;
        return pi;
    }

    private static int setPivot(int[] a, int... defaultIndex) {
        int i = -1;
        if (defaultIndex.length == 1) {
            int defi = defaultIndex[0];
            if (defi < 0 || defi >= a.length) {
                throw new IllegalArgumentException("defaultIndex: " + defi + " invalid for array length: " + a.length);
            }
            i = defi;
        } else {
            Random r = new Random(System.currentTimeMillis()); // no need for a repeatable random value
            i = r.nextInt(a.length);
        }
        int t = a[0];
        a[0] = a[i];
        a[i] = t;
        return i;
    }
}
