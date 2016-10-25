package tmp;

import org.junit.Test;

import static org.junit.Assert.*;
import static tmp.LongestContainedInterval.find;

/**
 * Created by kedar on 10/25/16.
 */
public class LongestContainedIntervalTest {
    @Test
    public void findTest() throws Exception {
        int[] a = new int[] {10, 5, 3, 11, 6, 100, 4};
        int[] r = find(a);
        assertEquals(3L, r[0]);
        assertEquals(6L, r[1]);
        a = new int[] {-4, 100, 101, 103, 102, 99, 3, -3, 1, -1, 0, 2, -2, 4};
        r = find(a);
        assertEquals(-4L, r[0]);
        assertEquals(4L, r[1]);
    }

}