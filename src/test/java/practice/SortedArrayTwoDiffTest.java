package practice;

import org.junit.Test;

import static org.junit.Assert.*;
import static practice.SortedArrayTwoDiff.isAnyDiff;

/**
 * Created by kmhaswade on 8/28/16.
 */
public class SortedArrayTwoDiffTest {

    @Test
    public void testIsAnyDiff() throws Exception {
        int[] a = new int[] {-10, -4, 0, 2, 7, 9}; //6, 10, 12, 17, 19, 4, 6, 11, 13, 2, 7, 9, 5, 7, 2 are the diff's
        int[] indexes1 = new int[2];
        int[] indexes2 = new int[2];
        boolean slow = isAnyDiffSlow(a, 11, indexes1);
        boolean fast = isAnyDiff(a, 11, indexes2);
        assertEquals(slow, fast); // true
        assertTrue(fast);
        assertArrayEquals(indexes1, indexes2);
        // test 2
        indexes1 = new int[2];
        indexes2 = new int[2];
        slow = isAnyDiffSlow(a, 18, indexes1);
        fast = isAnyDiff(a, 18, indexes2);
        assertEquals(slow, fast); // false
        assertFalse(fast);
    }

    static boolean isAnyDiffSlow(int[] a, int d, int[] indexes) {
        int length = a.length;
        for (int i = 0; i < length - 1; i++)
            for (int j = i + 1; j < length; j++) {
                if ((a[j] - a[i]) == d) {
                    indexes[0] = i;
                    indexes[1] = j;
                    return true;
                }
            }
        return false;
    }
}