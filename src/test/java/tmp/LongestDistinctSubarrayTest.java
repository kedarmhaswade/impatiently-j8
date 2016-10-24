package tmp;

import org.junit.Test;

import static org.junit.Assert.*;
import static tmp.LongestDistinctSubarray.longestSubarray;

/**
 * Created by kedar on 10/24/16.
 */
public class LongestDistinctSubarrayTest {
    @Test
    public void longestSubarrayTest() throws Exception {
        Character[] c = new Character[] {'f', 's', 'f', 'e', 't', 'w', 'e', 'n', 'w', 'e'};
        int[] maxDistinct = longestSubarray(c);
        assertEquals(1L, maxDistinct[0]);
        assertEquals(5L, maxDistinct[1]);
    }
}