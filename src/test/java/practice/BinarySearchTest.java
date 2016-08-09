package practice;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static practice.BinarySearch.ArrayListOfUnknownSize;
import static practice.BinarySearch.binarySearch;
/**
 * Created by kmhaswade on 8/3/16.
 */
public class BinarySearchTest {

    @Test
    public void testBinarySearch() throws Exception {
        ArrayListOfUnknownSize<Integer> list = ArrayListOfUnknownSize.asList(-2, 5, 10, 18, 53, 120, 200, 301, 427);
        assertEquals(2, binarySearch(list, 10));
        assertEquals(7, binarySearch(list, 301));
        assertEquals(-7, binarySearch(list, 300)); // 300 would at the place where 301 is, i.e. 7.
        assertEquals(8, binarySearch(list, 427)); // 427 is the last element.
        assertEquals(-9, binarySearch(list, 500)); // 500 would at index 9 (just beyond the last element 427).
    }
    @Test
    public void testWithLargeList() throws Exception {
        final int LIMIT_EX = 1_000_000;
        ArrayListOfUnknownSize<Integer> list = largeList(LIMIT_EX);
        list.set(LIMIT_EX - 1, LIMIT_EX);
        assertEquals(LIMIT_EX - 1, binarySearch(list, LIMIT_EX));
        assertEquals(-LIMIT_EX, binarySearch(list, LIMIT_EX + 111));
    }

    private ArrayListOfUnknownSize<Integer> largeList(int limit_ex) {
        Random r = new Random(System.currentTimeMillis());
        ArrayListOfUnknownSize<Integer> list = new ArrayListOfUnknownSize<>(limit_ex);
        for (int i = 0; i < limit_ex; i++) {
            list.add(r.nextInt(limit_ex));
        }
        return list;
    }
}