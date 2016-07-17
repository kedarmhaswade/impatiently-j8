package practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static practice.StockStrategy1.maxProfit;

/**
 * Created by kmhaswade on 7/16/16.
 */
public class StockStrategy1Test {

    @Test
    public void monotonicallyIncreasing() {
        double[] p = {1.0, 1.5, 2.0, 2.5, 3.5, 4.5, 6.0};
        List<Integer> bis = new ArrayList<>(1);
        List<Integer> sis = new ArrayList<>(1);
        double maxp = maxProfit(p, bis, sis);
        assertEquals(5.0, maxp, 0.001);
        assertEquals(0, (long) bis.get(0));
        assertEquals(6, (long) sis.get(0));
        assertEquals(1, bis.size());
        assertEquals(1, sis.size());
    }
    @Test
    public void monotonicallyDecreasing() {
        double[] p = {6.0, 4.5, 4.5, 3.5, 2.5, 2.0, 1.0};
        List<Integer> bis = new ArrayList<>(0);
        List<Integer> sis = new ArrayList<>(0);
        double maxp = maxProfit(p, bis, sis);
        assertEquals(0.0, maxp, 0.001);
        assertTrue(bis.isEmpty());
        assertTrue(sis.isEmpty());
    }
    @Test
    public void zigzag() {
        double[] p = {2.0, 2.5, 3.5, 1.0, 0.5, 1.5, 2, 2.1};
        List<Integer> bis = new ArrayList<>(2);
        List<Integer> sis = new ArrayList<>(2);
        double maxp = maxProfit(p, bis, sis);
        assertEquals(3.1, maxp, 0.001);
        assertEquals(0, (long) bis.get(0));
        assertEquals(2, (long) sis.get(0));

        assertEquals(4, (long) bis.get(1));
        assertEquals(7, (long) sis.get(1));
    }
}