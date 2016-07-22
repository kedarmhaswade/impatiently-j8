package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.StockStrategy2.maxProfit;

/**
 * Created by kmhaswade on 7/18/16.
 */
public class StockStrategy2Test {

    @Test
    public void givenTest() {
        double[] p = {310, 315, 275, 295, 260, 270, 290, 230, 255, 250};
        assertEquals(30, maxProfit(p), 0.0001); //4, 6
    }
    @Test
    public void zigzagLowHigh() {
        double[] p = {1, 10, 5, 15, 0.5};
        assertEquals(14, maxProfit(p), 0.0001); //0, 4
    }

}