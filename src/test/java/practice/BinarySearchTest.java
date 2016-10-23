package practice;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static practice.BinarySearch.*;

/**
 * Created by kmhaswade on 8/3/16.
 */
public class BinarySearchTest {

    @Test
    public void testBinarySearch() throws Exception {
        ArrayListOfUnknownSize<Integer> list = ArrayListOfUnknownSize.asList(-2, 5, 10, 18, 53, 120, 200, 301, 427);
        assertEquals(2, binarySearch(list, 10));
        assertEquals(7, binarySearch(list, 301));
        assertEquals(-8, binarySearch(list, 300)); // 300 would at the place where 301 is, i.e. 7.
        assertEquals(8, binarySearch(list, 427)); // 427 is the last element.
        assertEquals(-10, binarySearch(list, 500)); // 500 would at index 9 (just beyond the last element 427).
    }
    @Test
    public void testWithLargeList() throws Exception {
        final int LIMIT_EX = 1_000_000;
        ArrayListOfUnknownSize<Integer> list = largeList(LIMIT_EX);
        list.set(LIMIT_EX - 1, LIMIT_EX);
        assertEquals(LIMIT_EX - 1, binarySearch(list, LIMIT_EX));
        assertEquals(-(LIMIT_EX + 1), binarySearch(list, LIMIT_EX + 111));
    }

    private ArrayListOfUnknownSize<Integer> largeList(int limit_ex) {
        Random r = new Random(System.currentTimeMillis());
        ArrayListOfUnknownSize<Integer> list = new ArrayListOfUnknownSize<>(limit_ex);
        for (int i = 0; i < limit_ex; i++) {
            list.add(r.nextInt(limit_ex));
        }
        return list;
    }

    @Test
    public void firstOccurrenceTest() {
        int[] a = new int[] {1, 2, 3, 4, 4, 4, 5, 6, 7};
        assertEquals(3L, firstOccurrence(a, 4));
        a = new int[] {4, 4, 4, 4, 4, 4, 5, 6, 7};
        assertEquals(0L, firstOccurrence(a, 4));
        a = new int[] {0, 0, 1, 2, 3, 3, 5};
        assertEquals(3L, firstOccurrence(a, 2));
    }

    @Test
    public void firstGreaterThanTest() {
        int[] a = new int[] {1, 2, 3, 4, 6, 8, 10, 10, 11};
        assertEquals(0L, firstGreatThan(a, -1));
        assertEquals(1L, firstGreatThan(a, 1));
        assertEquals(2L, firstGreatThan(a, 2));
        assertEquals(6L, firstGreatThan(a, 8));
        assertEquals(8L, firstGreatThan(a, 10));
        assertEquals(-1L, firstGreatThan(a, 11));
        a = new int[] {-14, -10, 2, 108, 108, 243, 285, 285, 285, 401};
        assertEquals(9L, firstGreatThan(a, 285));
        assertEquals(1L, firstGreatThan(a, -13));
    }

    @Test
    public void rightRotateTest() {
        int[] rar = new int[] {2, 3, 4, 1};
        assertEquals(3L, findMinRightRotated(rar));
        rar = new int[] {2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 1};
        assertEquals(9L, findMinRightRotated(rar));
        rar = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}; // right rotate 0
        assertEquals(0L, findMinRightRotated(rar));
    }

    @Test
    public void integerSquareRootTest() {
        // 0
        assertEquals(0L, intSquareRoot(0));
        // 1
        assertEquals(1L, intSquareRoot(1));
        assertEquals(1L, intSquareRoot(2));
        assertEquals(1L, intSquareRoot(3));
        // 2
        assertEquals(2L, intSquareRoot(4));
        assertEquals(2L, intSquareRoot(5));
        assertEquals(2L, intSquareRoot(6));
        assertEquals(2L, intSquareRoot(7));
        assertEquals(2L, intSquareRoot(8));
        // 3
        assertEquals(3L, intSquareRoot(9));

        assertEquals(46340L, intSquareRoot(Integer.MAX_VALUE));
        assertEquals(1073741824L, intSquareRoot(1L<<60));
//        System.out.println(Math.sqrt(Long.MAX_VALUE));
//        assertEquals(3037000499L, intSquareRoot(Long.MAX_VALUE));
    }
}