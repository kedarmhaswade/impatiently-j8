package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.Monotones.howMany;
import static practice.Monotones.howManyRecursive;

/**
 * Created by kmhaswade on 8/23/16.
 */
public class MonotonesTest {

    @Test
    public void testHowMany() throws Exception {
        assertEquals(howManyRecursive(2), howMany(2));
        assertEquals(howManyRecursive(3), howMany(3));
        assertEquals(howManyRecursive(4), howMany(4));
        assertEquals(howManyRecursive(5), howMany(5));
        assertEquals(howManyRecursive(6), howMany(6));
        assertEquals(howManyRecursive(7), howMany(7));
        assertEquals(howManyRecursive(8), howMany(8));
        assertEquals(howManyRecursive(10), howMany(10));
        assertEquals(howManyRecursive(11), howMany(11));
    }
}