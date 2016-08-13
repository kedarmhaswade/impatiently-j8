package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.MaxSubarray.value;

/**
 * Created by kmhaswade on 8/12/16.
 */
public class MaxSubarrayTest {

    @Test
    public void testValue() throws Exception {
        assertEquals(20L, value(new int[]{1, 2, 3, 4, 10}));
        assertEquals(19L, value(new int[]{-11, 2, 3, 4, 10}));
        assertEquals(4L, value(new int[]{-1, -2, -3, 4, -10}));
        assertEquals(6L, value(new int[]{-2, 4, -3, 5, -10}));
        assertEquals(5L, value(new int[]{-1, 1, -2, 2, -3, 3, -4, 4, -5, 5}));
    }
}