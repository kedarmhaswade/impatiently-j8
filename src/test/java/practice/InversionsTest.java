package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.Inversions.count;

/**
 * Created by kmhaswade on 8/19/16.
 */
public class InversionsTest {

    @Test
    public void testCount() throws Exception {
        int[] a = new int[] {1, 4, 6, 8, 2, 3, 5, 7};
        // (4, 2), (6, 2), (8, 2)
        // (4, 3), (6, 3), (8, 3)
        // (6, 5), (6, 8)
        // (8, 7)
        assertEquals(9L, count(a));
    }
}