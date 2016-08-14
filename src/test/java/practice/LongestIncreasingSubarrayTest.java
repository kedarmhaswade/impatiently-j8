package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.LongestIncreasingSubarray.indexesFirst;

/**
 * Created by kmhaswade on 8/13/16.
 */
public class LongestIncreasingSubarrayTest {

    @Test
    public void testIndexes() throws Exception {
        int[] a = new int[]{2, 11, 3, 5, 13, 7, 19, 17, 23};
        int[] idx = indexesFirst(a);
        assertEquals(2L, idx[0]);
        assertEquals(4L, idx[1]);
    }
}