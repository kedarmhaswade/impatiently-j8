package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.KeyRemover.remove;

/**
 * Created by kmhaswade on 9/1/16.
 */
public class KeyRemoverTest {

    @Test
    public void testRemove() throws Exception {
        Integer[] a = new Integer[] {9, 7, 3, 5, 3, 2, 8, 3, 3, 3, 1};
        assertEquals(6L, remove(a, 3));
        a = new Integer[] {3, 3, 3, 3, 3};
        assertEquals(0L, remove(a, 3));
        a = new Integer[] {1, 3, 4, 6, 10};
        assertEquals(5L, remove(a, 11));
        a = new Integer[] {1, 3, 3, 12, 3, 3};
        assertEquals(2L, remove(a, 3));
    }
}