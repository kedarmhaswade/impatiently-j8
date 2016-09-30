package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.Calculator.polish;

/**
 * Created by kmhaswade on 9/21/16.
 */
public class CalculatorTest {

    @Test
    public void testPolish() throws Exception {
        String expr = "+, 5, 3";
        assertEquals(8L, polish(expr));
        expr = "/, *, 2, 10, 4";
        assertEquals(5L, polish(expr));
        expr = "-, /, *, 2, 10, 4, 5";
        assertEquals(0L, polish(expr));
        expr = "23";
        assertEquals(23L, polish(expr));
        expr = "*, -4, -3";
        assertEquals(12L, polish(expr));
    }
}