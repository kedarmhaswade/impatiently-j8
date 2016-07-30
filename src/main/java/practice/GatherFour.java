package practice;

import java.util.Arrays;

/**
 * <p>
 *     Given an array A of n objects <b>with key that takes one of the four values,</b>
 *     reorder the array so that all objects that have the same key appear together.
 * </p>
 * <p>
 *     Use O(1) additional space and O(n) time.
 * </p>
 * Created by kmhaswade on 7/28/16.
 */
public class GatherFour {
    /** Enums can be used to capture keys with finite values */
    enum Key {RED, BLUE, GREEN, YELLOW}

    /**
     * Accepts an array of Key objects and rearranges it such that equal keys are together. In reality, one should
     * accept an array of objects with keys being key objects, but this is a good enough simplification. Uses the trick
     * in {@linkplain Partitioner#partition(int[], int)} which arranges the objects such that keys are arranged such
     * that keys less than pivot, keys same as pivot and keys greater than pivot are together. Now, we can do one more
     * pass to separate the keys that are less than the current pivot by choosing another pivot. The limitation of
     * this method is that it needs at least one item (in the array) of each key.
     * @param a an array of {@linkplain Key}s
     */
    static void getTogether(Key[] a) {
        // find the first GREEN and use that as the pivot for the entire array.
        int pivotIndex = firstGreenIndex(a);
        int firstGreenIndex = partition(a, pivotIndex, 0, a.length);
        System.out.println(Arrays.toString(a));
        // now we have (RED+BLUE)S, GREENS, YELLOWS -- we need to separate REDS and BLUES
        int firstBlueIndex = partition(a, 0, 0, firstGreenIndex);
    }

    /**
     * Partitions the given array from index fromIn (inclusive) to index toEx (exclusive) and returns the first index
     * of the elements that are same as the pivot (element at pivotIndex). Equal elements are grouped together.
     * @param pivotIndex index of pivot element (fromIn &lt;= pivotIndex &lt; toEx
     * @param fromIn "left" index inclusive
     * @param toEx "right" index exclusive
     * @return index of the first element that is same as the pivot
     */
    static int partition(Key[] a, int pivotIndex, int fromIn, int toEx) {
        // bounds check
        int smaller = fromIn, equal = fromIn, larger = toEx;
        int ne = 0; // number of elements that are same as pivot
        Key pivot = a[pivotIndex];
        System.out.println("pivotIndex: " + pivotIndex + ", pivot is: "+ pivot + ", ordinal: " + pivot.ordinal());
        while (equal < larger) {
            if (a[equal].ordinal() < pivot.ordinal()) {
                swap(a, smaller++, equal++);
            } else if (a[equal].ordinal() == pivot.ordinal()) {
                equal++;
                ne++;
            } else {
                assert a[equal].ordinal() > pivot.ordinal();
                swap(a, equal, --larger); // note: --larger decrements first
            }
        }
        System.out.println("number of elements equal to pivot: " + ne + ", first  index: " + (equal - ne));
        return equal - ne;
    }

    private static void swap(Key[] a, int i, int j) {
        Key t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static int firstGreenIndex(Key[] a) {
        int length = a.length;
        for (int i = 0; i < length; i++)
            if (a[i] == Key.GREEN)
                return i;
        throw new IllegalStateException("We need at least one green ...");
    }
}
