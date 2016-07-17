package practice;

import java.util.List;

/**
 * <p>
 *     Say you are given an array of doubles (stock prices) in a period of time.
 *     What is the maximum profit that could have been had if
 *     <ul>
 *         <li> you could buy and sell as many times as you can </li>
 *         <li> of course, you needed to buy before you can sell </li>
 *     </ul>
 * </p>
 * Created by kmhaswade on 7/16/16.
 */
public class StockStrategy1 {
    static double maxProfit(double[] p, List<Integer> bis, List<Integer> sis) {
        int bi = 0; // the buy index
        int si; // the sell index
        double profit = 0.0;
        for (int i = 1; i < p.length; i++) {
            if (p[i] > p[bi] &&
                    ((i == p.length - 1) || (p[i] > p[i+1]))) {
                // sell point:  /\   or  /
                //             /        /
                si = i;
                sis.add(si);
                bis.add(bi);
                profit += (p[si] - p[bi]);
                // now reset bi as the next possible index
                bi = i + 1;
            } else if (p[i] < p[bi]) {
                bi = i;
            } else {
                // do nothing?
            }
        }
        return profit;
    }
}
