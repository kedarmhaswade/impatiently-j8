package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static practice.ThreeSum.exist3;

/**
 * Created by kmhaswade on 8/27/16.
 */
public class ThreeSumTest {

    @Test
    public void testExists3() throws Exception {
        int[] a = new int[] {11, 2, 5, 7, 4}; // sorted: [2, 4, 5, 7, 11]
        int sum = 22; // 4, 7, 11 whose indexes are: 3, 4 and then 1.
        int[] indexes = new int[3];
        boolean e3 = exist3(a, sum, indexes);
        assertTrue(e3);
        assertEquals(3L, indexes[0]);
        assertEquals(4L, indexes[1]);
        assertEquals(1L, indexes[2]);
    }
}