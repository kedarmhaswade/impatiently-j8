package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.BasicTwoSum.get;

/**
 * Created by kmhaswade on 8/25/16.
 */
public class BasicTwoSumTest {

    @Test
    public void testGet() throws Exception {
        int[] a = new int[] {-2, -1, 0, 3, 8, 10};
        int[] indexes = get(a, 18);
        assertEquals(4L, indexes[0]);
        assertEquals(5L, indexes[1]);
    }
}