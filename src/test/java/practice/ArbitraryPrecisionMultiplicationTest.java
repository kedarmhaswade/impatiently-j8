package practice;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static practice.ArbitraryPrecisionMultiplication.multiply;

/**
 * Created by kmhaswade on 8/31/16.
 */
public class ArbitraryPrecisionMultiplicationTest {

    @Test
    public void testMultiply() throws Exception {
        List<Integer> a = Arrays.asList(1, 9, 3, 7, 0, 7, 7, 2, 1);
        List<Integer> b = Arrays.asList(-7, 6, 1, 8, 3, 8, 2, 5, 7, 2, 8, 7);
        List<Integer> exp = Arrays.asList(-1, 4, 7, 5, 7, 3, 9, 5, 2, 5, 8, 9, 6, 7, 6, 4, 1, 2, 9, 2, 7);
        assertEquals(exp, multiply(a, b));
    }
}