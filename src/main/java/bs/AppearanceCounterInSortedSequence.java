package bs;


import java.util.Arrays;

/**
 * Given a *sorted* array of integers a and an integer x, find the number of times x occurs in a.
 */
public class AppearanceCounterInSortedSequence {

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 4, 5, 5, 22};
        System.out.println(countFastRecursive(a, 2));
        System.out.println(countFastIterative(a, 2));
        System.out.println(countSlow(a, 2));
    }

    public static final int countSlow(int[] a, int x) {
        int i = Arrays.binarySearch(a, x);
        int n = a.length;
        if (i == -1)
            return 0;
        else {
            int j = i;
            while (i >= 0 && a[i] == x)
                i--;
            while (j < n && a[j] == x)
                j++;
            return j - i - 1;
        }
    }

    public static final int countFastRecursive(int[] a, int x) {
        return countFastRecursive(a, x, 0, a.length);
    }

    public static final int countFastIterative(int[] a, int x) {
        return countFastIterative(a, x, 0, a.length);
    }

    private static final int countFastRecursive(int[] a, int x, int li, int hx) {
        int i = Arrays.binarySearch(a, x);
        if (i == -1)
            return 0;
        else {
            int j1 = lastIndexLessThanRecursive(a, x, li, i);     //  last index of the element < x in the first part
            int j2 = firstIndexGreaterThanRecursive(a, x, li, hx);// first index of the element > x in the second part
            assert j2 - j1 >= 2;
            return j2 - j1 - 1;
        }
    }

    private static final int countFastIterative(int[] a, int x, int li, int hx) {
        int i = Arrays.binarySearch(a, x);
        if (i == -1)
            return 0;
        else {
            int j1 = lastIndexLessThanIterative(a, x, li, i);     //  last index of the element < x in the first part
            int j2 = firstIndexGreaterThanIterative(a, x, li, hx);// first index of the element > x in the second part
            assert j2 - j1 >= 2;
            return j2 - j1 - 1;
        }
    }

    /**
     * Returns the <code>first</code> index of an element in the given array a that is greater than
     * the given element x.
     *
     * @param a
     * @param x
     * @param li low index, inclusive
     * @param hx high index, exclusive
     * @return lowest index i such that a[i] > x, if all the elements are same as x, return hx
     */
    private static int firstIndexGreaterThanRecursive(int[] a, int x, int li, int hx) {
        if (li == hx)
            return hx;
        int mi = (li + hx) >>> 1;
        if (a[mi] > x) {
            return firstIndexGreaterThanRecursive(a, x, li, mi);
        } else {
            return firstIndexGreaterThanRecursive(a, x, mi + 1, hx);
        }
    }

    private static int firstIndexGreaterThanIterative(int[] a, int x, int li, int hx) {
        while (li < hx) {
            int mi = (li + hx) >>> 1;
            if (a[mi] > x) {
                hx = mi;
            } else {
                li = mi + 1;
            }
        }
        return hx;
    }

    /**
     * Returns the <code>last</code> index of an element in the given array a that is <i>less</i> than the
     * given element x.
     *
     * @param a
     * @param x
     * @param li low index, inclusive
     * @param hx high index, exclusive
     * @return highest index i such that a[i] < x, if all the elements are same as x, return -1
     */
    private static int lastIndexLessThanRecursive(int[] a, int x, int li, int hx) {
        if (li == hx)
            return li - 1;
        int mi = (li + hx) >>> 1;
        if (a[mi] < x) {
            return lastIndexLessThanRecursive(a, x, mi + 1, hx);
        } else {
            return lastIndexLessThanRecursive(a, x, li, mi);
        }
    }
    private static int lastIndexLessThanIterative(int[] a, int x, int li, int hx) {
        while (li < hx) {
            int mi = (li + hx) >>> 1;
            if (a[mi] < x) {
                li = mi + 1;
            } else {
                hx = mi;
            }
        }
        return li - 1;
    }
}
