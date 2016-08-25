package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.Knapsack1.maxValue;

/**
 * Created by kmhaswade on 8/24/16.
 */
public class Knapsack1Test {

    @Test
    public void testMaxValue() throws Exception {
        int[] v = new int[] {60, 50, 70, 30};
        int[] w = new int[] {5, 3, 4, 2};
        assertEquals(80L, maxValue(w, v, 5));
    }
}