package practice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * <p>
 *     A few routines related to binary search technique. This technique is surprisingly useful and like Josh Bloch
 *     has said, implementing binary search right takes practice!
 * </p>
 * Created by kmhaswade on 8/3/16.
 */
public final class BinarySearch {

    /**
     * Throws {@linkplain IndexOutOfBoundsException} when an invalid access is made.
     * @param <E>
     */
    static final class ArrayListOfUnknownSize<E extends Comparable<E>> extends ArrayList<E> {
        @Override
        public int size() {
            throw new UnsupportedOperationException("not known");
        }

        public ArrayListOfUnknownSize(int capacity) {
            super(capacity);
        }

        public static <E extends Comparable<E>> ArrayListOfUnknownSize<E> asList(E... es) {
            ArrayListOfUnknownSize<E> list = new ArrayListOfUnknownSize<>(es.length);
            Arrays.stream(es).forEach(list::add);
            return list;
        }
    }

    /**
     * Performs a binary search on the given sorted list of unknown size to find the index of given key. The results
     * are undefined if the list is not sorted. Since the size of the given {@linkplain ArrayListOfUnknownSize} is
     * unknown, divide and conquer is used to find its length. Returns the negative of the first index where the key
     * would be found in case of an unsuccessful search. This is similar to JDK's various
     * {@link Arrays#binarySearch(int[], int)} routines.
     * @param list
     * @param key
     * @param <E>
     * @return a positive number indicating the index of the key if it exists, a negative number indicating the
     * possible index of the key if it does not exist.
     */
    public static <E extends Comparable<E>> int binarySearch(ArrayListOfUnknownSize<E> list, E key) {
        int p = 0;
        int index;
        while (true) {
            index = (1 << p) - 1;
            try {
                E candidate = list.get(index);
                if (candidate.compareTo(key) == 0) {
                    return index;
                } else if (candidate.compareTo(key) > 0) {
                    break;
                }
                p += 1;
            } catch (IndexOutOfBoundsException e) {
                break;
            }
        }
        // we either ran into an element that is larger or we ran out of the list at index 2^(p) - 1;
        int low = 1 << (p - 1);  // low index is inclusive
        int high = (1 << p) - 2; // high index is also inclusive
        while (low <= high) {
            int mid = (low + high) >>> 1;
            try {
                E midVal = list.get(mid);
                if (midVal.compareTo(key) == 0) {
                    return mid;
                } else if (midVal.compareTo(key) < 0) {
                    low = mid + 1;
                } else {
                    assert midVal.compareTo(key) > 0;
                    high = mid - 1;
                }
            } catch (IndexOutOfBoundsException e) {
                high = mid - 1;
            }
        }
        return - (low + 1);
    }

    /**
     * Given a sorted array, finds the <i>first occurrence</i> of the given key.
     * @param a the array to search in, assumed to be sorted
     * @param key the key to search for
     * @return If the key is <i>not</i> found, this method returns what {@linkplain Arrays#binarySearch(int[], int) JDK's binary search}
     * returns (read it carefully). <br/>
     * If the key is found, returns the index of its first occurrence (always non-negative).
     */
    public static int firstOccurrence(int[] a, int key) {
        int i = Arrays.binarySearch(a, key);
        if (i < 0)
            return i;
        // the key is found, now find the last index of the element that is NOT the same as key
        int fromInc = 0; // it could be as early as 0
        int toInc = i; // a[toInc] = key
        while (fromInc < toInc) {
            int mid = (fromInc + toInc) >>> 1;
            int midVal = a[mid];
            if (midVal == key)
                toInc = mid;
            else if (midVal < key)
                fromInc = mid + 1;
            else
                throw new AssertionError("assumptions challenged");
        }
        return fromInc;
    }

    /**
     * Returns the index of the first element <i>greater than</i> the given key in a given <i>sorted</i> array.
     * @param a sorted array to search in
     * @param key the key to search for
     * @return
     * <ol>
     *     <li> 0, if the key is the smaller than the 0th element of a</li>
     *     <li> -1 if they key is greater than the last element of a</li>
     *     <li> a positive integer denoting the index of the first element greater than key</li>
     * </ol>
     */
    public static int firstGreatThan(int[] a, int key) {
        int loIn = 0;
        int hiEx = a.length;
        while (loIn < hiEx) {
            int mid = (loIn + hiEx) >>> 1;
            int midVal = a[mid];
            if (key >= midVal)
                loIn = mid + 1;
            else {
                assert key < midVal;
                hiEx = mid;
            }
        }
        if (loIn == a.length)
            return -1; // all elements are <= key
        return loIn;
    }

    /**
     * <p>
     *     Given a sorted array that is right-rotated, such that the minimum element lies somewhere in the
     *     middle (a wedge is created), this method efficiently finds the index of that minimum element.
     * </p>
     * @param rar the right-rotated array
     * @return the index of the minimum element
     */
    public static int findMinRightRotated(int[] rar) {
        final int len = rar.length;
        // special case lengths <= 3?
        int lo = 0;
        int hi = len;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            int midVal = rar[mid];
            int prevIndex = mid - 1 < 0 ? hi - 1 : mid - 1;
            int nextIndex = mid + 1 < len ? mid + 1 : 0;
            if (midVal <= rar[prevIndex] && midVal <= rar[nextIndex])
                return mid;
            if (midVal > rar[hi - 1])
                lo = mid;
            else
                hi = mid;
        }
        throw new AssertionError("can't reach here: lo: " + lo + ", hi: " + hi);
    }

    /**
     * <p>
     *     Given a non-negative integer n, finds another <i>integer</i> whose square is less than or equal to n.
     * </p>
     * @param n the given number
     * @return see above
     */
    public static long intSquareRoot(long n) {
        if (n < 0)
            throw new IllegalArgumentException("negative n: " + n);
        if (n == 0)
            return 0;
        long pr = 2;
        long nr = n/pr;
        while (true) {
            long mid = (pr + nr) >>> 1;
//            System.out.println(pr + ", " + nr + ", " + mid);
            long sq, nextSq;
            try {
                sq = Math.multiplyExact(mid, mid);
                nextSq = Math.multiplyExact(mid + 1, mid + 1);
            } catch(ArithmeticException a) {
                nr = mid;
                continue;
            }
            if (sq == n || ((sq < n) && (nextSq > n)))
                return mid;
            else if (sq > n)
                nr = mid;
            else {
                assert sq < n;
                pr = mid;
            }
        }
    }
}
