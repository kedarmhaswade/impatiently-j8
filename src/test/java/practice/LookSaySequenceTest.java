package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.LookSaySequence.generate;

/**
 * Created by kmhaswade on 7/31/16.
 */
public class LookSaySequenceTest {

    @Test
    public void testGenerate() throws Exception {
        assertEquals("312211", generate(6));
    }
}