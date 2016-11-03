package practice;

/**
 * <p>This is a variation of {@linkplain StockStrategy1}. This program, when given a list of stock prices, finds the
 * maximum profit when you are allowed to buy and sell once. The only restriction is that you have to buy before you
 * can sell.
 * </p>
 *
 * Created by kmhaswade on 7/18/16.
 */
public class StockStrategy2 {

    static double maxProfit(double[] p) {
        double minSoFar = Double.MAX_VALUE;
        double maxp = Double.MIN_VALUE;
        for (int i = 0; i < p.length; i++) {
            minSoFar = Math.min(minSoFar, p[i]);
            double profit = p[i] - minSoFar; // this is the profit if we choose to sell on the ith day
            maxp = Math.max(maxp, profit);
        }
        return maxp;
    }
}
