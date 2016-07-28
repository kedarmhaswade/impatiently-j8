package practice;

/**
 * <p>
 *     Given an array a of integers and an index i (0 &lt;= i &lt; a.length), rearrange a such that
 *     all the elements of a less than a[i] come before all the elements that are same as a[i]
 *     which come before all the elements greater than a[i]. Return the index where the
 *     elements equal to a[i] start.
 * </p>
 * Created by kmhaswade on 7/27/16.
 */
public class Partitioner {
    /**
     * <p>
     * Naively partitions the given array. The partitioned array follows the property:
     * a[i] &lt;= a[p] for all i <=p and a[i] &gt; a[p] a[i] for all i &gt; p, but not necessarily
     * the property defined in the class documentation: {@linkplain Partitioner}.
     * </p>
     * @param a
     * @param i
     * @return
     */
    static int naivePartition(int[] a, int i) {
        // check bounds
        swap(a, i, 0);
        int pivot = a[0],  j = 0;
        for (int k = 1; k < a.length; k++) {
            int curr = a[k];
            if (curr <= pivot) {
                a[k] = a[j + 1];
                a[j + 1] = a[j];
                a[j] = curr;
                j += 1;
            }
        }
        return j;
    }
    static int partition(int[] a, int i) {
        // check bounds
        swap(a, i, 0);
        int pivot = a[0];
        int ne = 1; // number of elements equal to pivot
        int ei = 0; // index of the latest element that is equal to pivot
        final int length = a.length;
        for (int k = 1; k < length; k++) {
            int curr = a[k];
            if (curr < pivot) {
                a[k] = a[ei + 1];
                a[ei + 1] = a[ei];
                a[ei - ne + 1] = curr;
                ei += 1;
            } else if (curr == pivot) {
                a[k] = a[ei + 1];
                a[ei + 1] = curr;
                ei += 1;
                ne += 1;
            } else {
                // do nothing
            }
        }
        return ei - ne + 1;
    }
    static void swap(int[] a, int i, int j) {
        // no bounds checking
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
