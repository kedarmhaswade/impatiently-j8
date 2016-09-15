package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.SpiralOrdering.to2DClockwise;

/**
 * Created by kmhaswade on 9/4/16.
 */
public class SpiralOrderingTest {

    @Test
    public void testTo2DClockwise() throws Exception {
        int[] spiral = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        int[][] matrix = to2DClockwise(spiral);
        assertEquals(1L, matrix[0][0]);
        assertEquals(2L, matrix[0][1]);
        assertEquals(3L, matrix[0][2]);
        assertEquals(4L, matrix[0][3]);
        assertEquals(5L, matrix[1][3]);
        assertEquals(6L, matrix[2][3]);
        assertEquals(7L, matrix[3][3]);
    }
}