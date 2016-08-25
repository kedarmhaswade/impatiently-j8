package practice;

import java.util.ArrayList;
import java.util.Arrays;

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
            Arrays.stream(es).forEach(e -> list.add(e));
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
}
