package tmp;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Implements the classic partitioning routine. Chooses a pivot and partitions the given
 * array of integers around that.
 */
public class ClassicPartition {

    /**
     * Partitions the given array in place in O(n) time. Chooses a random pivot which is placed at the 0th index.
     * The invariant maintained is that after every number (starting from index 1) is read, the pivot could
     * be placed at the index that is its correctly sorted position in the subarray examined until then.
     *
     * @param a an integer array that is partitioned in place
     * @return integer representing the index of the pivot; if this method returns a number i, then the following holds:
     * <pre>
     *     a[j] < pivot for all 0 <= j < i, and
     *     a[j] >= pivot for all i+1 <= j < a.length
     * </pre>
     * I believe Nick Lomuto has a very similar routine, but I can say that I came up with this independently.
     */
    static int partition(int[] a, int lo, int hi) {
        setPivot(a, lo, hi);
        int pivot = a[lo];
//        System.out.println("pivot: " + pivot);
        int pi = 0; //pi is the sorted index of the pivot
        for (int i = lo + 1; i < hi; i++) {
            if (a[i] < pivot) {
                a[pi] = a[i];
                a[i] = a[pi + 1];
                pi += 1;
            }
        }
        a[pi] = pivot;
        return pi;
    }

    static int partition(int[]a) {
        return partition(a, 0, a.length);
    }

    private static int setPivot(int[] a, int lo, int hi, int... defaultIndex) {
        // remember: li is inclusive and hi is exclusive
        if (hi - lo < 1) {
            throw new IllegalArgumentException("low: " + lo + ", high: " + hi + " fail the contract");
        }
        int i;
        if (defaultIndex.length == 1) {
            int defi = defaultIndex[0];
            if (defi < lo || defi >= hi) {
                throw new IllegalArgumentException("defaultIndex: " + defi + " invalid for given low (incl): " + lo + " and high (excl): " + hi);
            }
            i = defi;
        } else {
            i = ThreadLocalRandom.current().nextInt(lo, hi);
        }
        int t = a[0];
        a[0] = a[i];
        a[i] = t;
        return i;
    }
}
