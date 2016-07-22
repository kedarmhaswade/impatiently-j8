package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.LongestEqualSubarray.longestSubarray;

/**
 * Created by kmhaswade on 7/18/16.
 */
public class LongestEqualSubarrayTest {

    @Test
    public void trivialAllEqual() {
        int[] a = {3, 3, 3, 3, 3, 3}; //6 threes
        assertEquals(a.length, longestSubarray(a));
    }
    @Test
    public void trivialDistinct() {
        int[] a = {1, 2, 3, 4, 5, 6};
        assertEquals(1, longestSubarray(a));
    }
    @Test
    public void monotonicallyIncreasing() {
        int[] a = {1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5};
        assertEquals(5, longestSubarray(a));
    }
    @Test
    public void monotonicallyDecreasing() {
        int[] a = {5, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3,  2, 2, 1};
        assertEquals(5, longestSubarray(a));
    }
    @Test
    public void zigZag() {
        int[] a = {1, 1, 1, 2, 2, 3, 3, 3, 3};
        assertEquals(4, longestSubarray(a));
    }
}