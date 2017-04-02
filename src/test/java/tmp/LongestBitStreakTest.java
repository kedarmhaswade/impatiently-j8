package tmp;

import org.junit.Test;

import static org.junit.Assert.*;
import static tmp.LongestBitStreak.*;

/**
 * Created by kedar on 3/5/17.
 */
public class LongestBitStreakTest {
    @Test
    public void basic()  {
        assertEquals(1L, get(5, 1));
        assertEquals(2L, get(13, 1));
        assertEquals(64L, get(-1, 1));
        assertEquals(3L, get(439, 1));
        assertEquals(1L, get(Long.MIN_VALUE, 1));
    }
    @Test
    public void visual() {
        assertEquals(5L, get(0b111100111011111001, 1)); // the rightmost streak is longest
    }
    @Test
    public void foolProof() {
        long n = 0b11111000001111100110101010;
        long nc = ~n;
        System.out.println(get(n, 1));
        System.out.println(get(nc, 0));
        assertEquals(get(n, 1), get(nc, 0));
        assertEquals(get(n, 0), get(nc, 1));
    }

}