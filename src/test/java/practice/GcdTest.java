package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.Gcd.binary;

/**
 * Created by kmhaswade on 8/9/16.
 */
public class GcdTest {

    @Test
    public void testBinary() throws Exception {
        assertEquals(12L, binary(24, 300));
    }
}