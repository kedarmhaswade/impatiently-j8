package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.MinUnchangeableAmount.find;

/**
 * Created by kmhaswade on 8/10/16.
 */
public class MinUnchangeableAmountTest {

    @Test
    public void testFind() throws Exception {
        assertEquals(9, find(new int[]{1, 1, 2, 4, 10}));
    }
}