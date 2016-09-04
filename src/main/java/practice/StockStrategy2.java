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
        double maxp = 0.0;
        int pbi = 0; // possible buy index, because what if no better sell price comes later?
        int bi = -1, si = -1; // buy and sell indexes corresponding to the max profit
        for (int i = 1; i < p.length; i++) {
            double diff = p[i] - p[pbi];
            if (diff > maxp) {
                maxp = diff;
                si = i;
                bi = pbi;
            }
            if (p[i] < p[pbi]) {
                if (p[pbi] > p[i])
                    pbi = i;
            }
        }
        if (maxp > 0) {
            System.out.println("occurs with buying at: " + bi + ", for: " + p[bi] + " and selling at: " + si + " for:" +
                    " " + p[si]);
        }
        return maxp;
    }
}
