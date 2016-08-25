package practice;

/**
 * <p>
 *     There are several versions of the knapsack problem. We can use dynamic programming to solve them in practice.
 *     We are given two properties, weight and value, of n items numbered 0 to n-1. We are asked to find the maximum
 *     value vmax that we can have by choosing the subset of items such that their weight is &le;= a constraint wmax.
 * </p>
 * <p>
 *     We use the standard DP formulation with a space optimization. We need O(wmax) space and O(n.wmax) time.
 * </p>
 * Created by kmhaswade on 8/24/16.
 */
public class Knapsack1 {

    static int maxValue(int[] w, int[] v, int wmax) {
        int[] values = new int[wmax + 1];
        int length = w.length;
        System.out.println("length: " + length);
        assert length == v.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= wmax; j++) {
                if (w[i] <= wmax) {
                    int vWithI = j - w[i] < 0 ? 0 : values[j - w[i]] + v[i];
                    int wWithoutI = values[j];
                    values[j] = Math.max(vWithI, wWithoutI);
                }
            }
        }
        return values[wmax];
    }
}
