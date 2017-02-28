package practice;

import org.junit.Test;

import static org.junit.Assert.*;
import static practice.MinUnsortedSubarray.get;

/**
 * Created by kedar on 2/15/17.
 */
public class MinUnsortedSubarrayTest {
    @Test
    public void basicGet() throws Exception {
        int[] a = new int[] {1, 3, 18, 5, 28, 10, 35, 12};
        int[] m = get(a);
        assertEquals(2, m[0]);
        assertEquals(7, m[1]);

        a = new int[] {6, 8, 20, 54, 23, 65, 70};
        m = get(a);
        assertEquals(3, m[0]);
        assertEquals(4, m[1]);

        a = new int[] {4, 10, 5, 6};
        m = get(a);
        assertEquals(1, m[0]);
        assertEquals(3, m[1]);

        a = new int[] {5, 3, 2, 1, 10, 12};
        m = get(a);
        assertEquals(0, m[0]);
        assertEquals(3, m[1]);

        a = new int[] {1, 3, 2, 4, 5, 12};
        m = get(a);
        assertEquals(1, m[0]);
        assertEquals(2, m[1]);

        a = new int[] {-3, 1, 3, 5, 6, 11, 41}; // sorted
        m = get(a);
        assertEquals(-1, m[0]);
        assertEquals(-1, m[1]);


        a = new int[] {-1, 2, 4, 5, 10, 23, 13, 14, 20}; //sorted and thenx unsorted among themselves
        m = get(a);
        assertEquals(5, m[0]);
        assertEquals(8, m[1]);


        a = new int[] {1, 3, 5, 10, 4};
        m = get(a);
        assertEquals(2, m[0]);
        assertEquals(4, m[1]);

        a = new int[] {100, 34, 23, 10, 8, 3, 2, 1}; // reverse sorted
        m = get(a);
        assertEquals(0, m[0]);
        assertEquals(7, m[1]);
    }

}