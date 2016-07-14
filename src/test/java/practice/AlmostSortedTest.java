package practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static practice.AlmostSorted.Sorted;
import static practice.AlmostSorted.isSorted;

/**
 * Created by kmhaswade on 7/13/16.
 */
public class AlmostSortedTest {

    @Test
    public void testSwap1() throws Exception {
        int[] a = new int[] {1, 5, 4, 8, 9};
        List<Integer> indexes = new ArrayList<>(2);
        Sorted state = isSorted(a, indexes);
        assertEquals(Sorted.swap, state);
        assertEquals(2, (int) indexes.get(0));
        assertEquals(3, (int) indexes.get(1));
    }
    @Test
    public void testSwap2() throws Exception {
        int[] a = new int[] {1, 5, 4, 3, 9};
        List<Integer> indexes = new ArrayList<>(2);
        Sorted state = isSorted(a, indexes);
        assertEquals(Sorted.swap, state);
        assertEquals(2, (int) indexes.get(0));
        assertEquals(5, (int) indexes.get(1));
    }

    @Test
    public void testReverse1() throws Exception {
        int[] a = new int[] {1, 5, 4, 3, 2, 9};
        List<Integer> indexes = new ArrayList<>(2);
        Sorted state = isSorted(a, indexes);
        assertEquals(Sorted.reverse, state);
        assertEquals(2, (int) indexes.get(0));
        assertEquals(5, (int) indexes.get(1));
    }

    @Test
    public void testSorted() throws Exception {
        int[] a = new int[] {1, 2, 3, 5, 9, 11};
        List<Integer> indexes = new ArrayList<>(2);
        Sorted state = isSorted(a, indexes);
        assertEquals(Sorted.yes, state);
    }

    @Test
    public void testReverse2() throws Exception {
        int[] a = new int[] {1, 5, 4, 3, 2, 1, 0};
        List<Integer> indexes = new ArrayList<>(2);
        Sorted state = isSorted(a, indexes);
        assertEquals(Sorted.no, state);
    }
    @Test
    public void testReverse3() throws Exception {
        int[] a = new int[] {-1, 63, 34, 22, 12, 10, 0};
        List<Integer> indexes = new ArrayList<>(2);
        Sorted state = isSorted(a, indexes);
        assertEquals(Sorted.reverse, state);
        assertEquals(2, (int) indexes.get(0));
        assertEquals(7, (int) indexes.get(1));
    }

}