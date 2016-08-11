package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.MaxProduct.find;

/**
 * Created by kmhaswade on 8/10/16.
 */
public class MaxProductTest {

    @Test
    public void testFind() throws Exception {
        assertEquals(60, find(new int[]{3, 2, 5, 4}));
        assertEquals(24, find(new int[]{3, 2, -1, 4}));
        assertEquals(72, find(new int[]{3, 2, -1, 4, -1, 6}));
        assertEquals(0, find(new int[]{3, 0, -1, 4, -1, 6, 3, 55, 0}));
        assertEquals(0, find(new int[]{3, 0, -1, 4, -1, 6, 3, 55, 0}));
        assertEquals(0, find(new int[]{3, 0, -1, 4, 5, 1}));
        assertEquals(60, find(new int[]{3, 0, -1, 4, 5, -1}));
    }
}