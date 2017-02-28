package practice;

import java.util.Arrays;

/**
 * <p>
 * Given an array, return the indexes of the minimum unsorted subarray, without modifying the array. <br/>
 *
 * A minimum unsorted subarray is defined as the subarray of min length that, when sorted, <br/>
 * would make the entire array sorted. <br/>
 *
 * When the array is already sorted, there is no unsorted subarray, in which case, return an array [-1, -1]
 * </p>
 * Created by kedar on 2/13/17.
 */
public class MinUnsortedSubarray {

    private static final int[] NO_SUCH = new int[] {-1, -1};

    /**
     * Returns the minimum unsorted subarray. See {@link MinUnsortedSubarray} for the definition.
     * @param a the array
     * @return the indexes of the min unsorted subarray as a 2-element array, [-1, -1] if the array is already sorted
     */
    public static int[] get(int[] a) {
        int max = a[0], // max element so far
            minu = Integer.MAX_VALUE,  // min element in the unsorted array
            len = a.length;
        int si = -1, // index of the element where the min unsorted array starts
            ei = -1, // index of the element where the min unsorted array ends
            lasti = 0, // index of the last element in sorted order
            i = 1;  // index of current element (under consideration)

        // note: a[0..0] is always sorted
        while (i < len) {
            int c = a[i];
            if (c > max) {
                max = c;
                if (si == ei) { // the order has not been destroyed yet
                    si = ei = lasti = i;
                }
            } else { // we found the first element that is "out of order"
                ei = i;
                minu = Math.min(minu, c);
            }
            i += 1;
        }
        if (si == ei) { // no element was out of order
            return NO_SUCH;
        }
        si = ((si = Arrays.binarySearch(a, 0, lasti + 1, minu)) < 0) ? -(si + 1) : si;
        // is linear search better?
        return new int[] {si, ei};
    }

}
/*
  The loop that does not work:
          while (i < len) {
            int c = a[i];
            if (c > max) {
                max = c;
                if (si == ei) {
                    si = ei = i;
                }
            } else if (c < min) {
                min = c;
                si = 0; // this minimum element has got to go to 0th index
                ei = i;
            } else { // c is between min and max
                ei = i;
            }
            i += 1;
        }
 */