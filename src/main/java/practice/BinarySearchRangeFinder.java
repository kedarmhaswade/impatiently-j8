package practice;

/**
 * <p>Binary search is very versatile. Suppose we have an array of contiguous interval lengths and we wanted that
 * interval within which a given number lies, then one way is to do a linear search. But a better way
 * is to do a binary search. Binary search works when the elements of array are monotonically ordered.</p>
 *
 * <p><pre>
 *     int[] a = {50, 70, 80, 100, 140, 195, 210} where the elements are interval ranges:
 *     [0, 50), [50, 70), [70, 80), [80, 100), [100, 140), [140, 195), [195, 210).
 *
 *     Find the element that corresponds to 95 (100, which is the smallest number that is bigger than 95).
 * </pre></p>
 */
public class BinarySearchRangeFinder {

    /**
     * Returns the index of the element in the given array that represents the interval that can hold
     * the given number n. If all the elements are greater or smaller than the given number,  -1 is returned.
     * @param a array of int ranges
     * @param n the target number
     * @return the index of the holding interval, -1 otherwise. -1 is returned only if the search fails
     */
    static int findRange(int[] a, int n) {
        if (n < 0 || a[a.length - 1] <= n) {
            return -1;
        }
        // we are now sure that a range can hold the given number
        // also note that the intervals are semi-open: [a, b), [b, c) means that b is in the second interval
        int length = a.length;
        int lo = 0;
        int hi = a.length; // exclusive
        while (true) {
            int midi = (hi + lo) >>> 1; // avoid overflow
            int mid = a[midi];
            if (mid == n || (hi - lo) == 1) {
                return midi + 1; // semi-open intervals
            } else if (mid > n) {
              if (mid == lo) {
                  return midi;
              }
              hi = midi;
            } else { // mid < n
                if (mid == hi) {
                    return midi;
                }
                lo = midi;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {50, 70, 80, 100, 140, 195, 210, 300, 400, 600, 650};
        int midi = findRange(a, 650);
        if (midi != -1) {
            System.out.println("mid index: " + midi + ", upper bound: " + a[midi]);
        }
    }
}
