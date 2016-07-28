package practice;

import org.junit.Test;

import java.util.Random;

import static java.lang.Long.bitCount;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static practice.BitwiseExamples.isPowerOf2;
import static practice.BitwiseExamples.popCount;
import static practice.BitwiseExamples.rightPropagateRightmostOne;

/**
 * Created by kmhaswade on 7/25/16.
 */
public class BitwiseExamplesTest {

    @Test
    public void simplePopCountTests() {
        long x = 1; // i.e. 0000000_0001
        assertEquals(1, popCount(x));
        x = 2; //i.e. 0000000_0010
        assertEquals(1, popCount(x));
        x = 3; //i.e. 0000000_0011
        assertEquals(2, popCount(x));
        x = 4; //i.e. 0000000_0100
        assertEquals(1, popCount(x));
        x = 5; //i.e. 0000000_0101
        assertEquals(2, popCount(x));
        x = 6; //i.e. 0000000_0110
        assertEquals(2, popCount(x));
        x = 7; //i.e. 0000000_0111
        assertEquals(3, popCount(x));
        x = -1; // i.e. 1111_1111_1111_1111_1111_1111_1111_1111
        assertEquals(64, popCount(x));
        x = Long.MIN_VALUE; // i.e. 1000_0000_0000_0000_0000_0000_0000_0000
    }
    @Test
    public void delegateToJdkPopCount() {
        // assumes that the JDK implementation is correct :-)
        Random r = new Random();
        for (int i = 0; i < 1_000; i++) {
            long x = r.nextLong();
            // expected value comes from the JDK
            assertEquals(bitCount(x), popCount(x));
        }
    }
    @Test
    public void propagateRightmostOneBasic() {
        long x   = 0b0000_0000_0000_0000_0000_0000_0000_1000;
        long exp = 0b0000_0000_0000_0000_0000_0000_0000_1111;
        assertEquals(exp, rightPropagateRightmostOne(x));
        x   = -1L; // this is all 1's
        exp = -1L;
        assertEquals(exp, rightPropagateRightmostOne(x));
        x   = Long.MIN_VALUE; // 0b1000_0000_0000_0000_0000_0000_0000_0000
        exp = -1L;            // 0b1111_1111_1111_1111_1111_1111_1111_1111
        assertEquals(exp, rightPropagateRightmostOne(x));
        x   = 0b0000_1000_0000_0000_0000_0000_0000_0000;
        exp = 0b0000_1111_1111_1111_1111_1111_1111_1111;
        assertEquals(exp, rightPropagateRightmostOne(x));
    }
    @Test
    public void testPositiveCaseForPowerOf2() {
        long n = 1;
        for (int i = 1; i <= 63; i++) { // rightshift 63 times
            assertTrue(isPowerOf2(n));
            n <<= 1;
        }
    }
    @Test
    public void testForKnownPowersOf2() {
        long low = 128;
        long high = 256;
        assertTrue(isPowerOf2(low));
        assertTrue(isPowerOf2(high));
        for (long i = low + 1; i < high; i++) {
            assertFalse(isPowerOf2(i));
        }
    }
    @Test
    public void assertThatNegativeNumberIsNotPowerOf2() {
        assertFalse(isPowerOf2(Long.MIN_VALUE));
        assertFalse(isPowerOf2(0b1000_0000_0000_0000_0000_0000_0000_0001));
    }
}