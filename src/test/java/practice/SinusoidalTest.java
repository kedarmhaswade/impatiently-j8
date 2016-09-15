package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.Sinusoidal.lrtb;

/**
 * Created by kmhaswade on 9/14/16.
 */
public class SinusoidalTest {

    @Test
    public void testLrtb() throws Exception {
        assertEquals("e lhlowrdlo", lrtb("hello world"));
    }
}