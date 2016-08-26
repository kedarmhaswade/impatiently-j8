package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.Knapsack1.maxValue;
import static practice.Knapsack1.maxValueOptimized;

/**
 * Created by kmhaswade on 8/24/16.
 */
public class Knapsack1Test {

    @Test
    public void testMaxValue() throws Exception {
        int[] v = new int[] {60, 50, 70, 30};
        int[] w = new int[] {5, 3, 4, 2};
        assertEquals(maxValue(w, v, 5), maxValueOptimized(w, v, 5));
        v = new int[] {65, 35, 245, 195};
        w = new int[] {20, 8, 60, 55};
        assertEquals(maxValue(w, v, 130), maxValueOptimized(w, v, 130));
        assertEquals(maxValue(w, v, 135), maxValueOptimized(w, v, 135));
    }
}