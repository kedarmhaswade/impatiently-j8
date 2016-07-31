package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.TrueStableBooleanPartition.Item;
import static practice.TrueStableBooleanPartition.partition;

/**
 * Created by kmhaswade on 7/30/16.
 */
public class TrueStableBooleanPartitionTest {

    @Test
    public void testPartition() throws Exception {
        Item[] a = {
                new Item(false, 1),
                new Item(true, 1), // relative order 1
                new Item(true, 2), // relative order 2
                new Item(true, 3), // relative order 3
                new Item(false, 2)
        };
        int ti = partition(a);
        assertEquals(2, ti);
        assertEquals(1, a[2].data);
        assertEquals(2, a[3].data);
        assertEquals(3, a[4].data);
    }
}