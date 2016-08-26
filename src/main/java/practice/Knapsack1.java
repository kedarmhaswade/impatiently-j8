package practice;

/**
 * <p>
 *     There are several versions of the knapsack problem. We can use dynamic programming to solve them in practice.
 *     We are given two properties, weight and value, of n items numbered 0 to n-1. We are asked to find the maximum
 *     value vmax that we can have by choosing the subset of items such that their weight is &le;= a constraint wmax.
 * </p>
 * <p>
 *     We use the standard DP formulation with a space optimization. We need O(n.capacity) space and O(n.capacity) time.
 *     An optimization can be to reduce the space requirements to O(capacity).
 * </p>
 * Created by kmhaswade on 8/24/16.
 */
public class Knapsack1 {

    static int maxValue(int[] w, int[] v, int capacity) {
        int length = w.length;
        assert length == v.length;
        int[][] values = new int[length][capacity + 1];
        for (int i = 0; i < length; i++) {
            for (int j = 0; j <= capacity; j++) {
                if (w[i] <= j) { // only now may we pick the ith item
                    int vWithI = i - 1 < 0 ? v[i] : values[i - 1][j - w[i]] + v[i];
                    int wWithoutI = i - 1 < 0 ? 0 : values[i-1][j];
                    values[i][j] = Math.max(vWithI, wWithoutI);
                } else {
                    values[i][j] = i - 1 < 0 ? 0 : values[i-1][j];
                }
            }
//            Utils.print(values[i]);
        }
        return values[length - 1][capacity];
    }

    static int maxValueOptimized(int[] w, int[] v, int capacity) {
        int nItems = w.length;
        assert nItems == v.length;
        int[] values = new int[capacity + 1];
        for (int j = 0; j <= capacity; j++) { // special treatment for the first item
            if (j >= w[0])
                values[j] = v[0];
        }
        // now treat the remaining items, preserving the recurrence
        for (int i = 1; i < nItems; i++) {
            int[] newValues = new int[capacity + 1];
            for (int j = 0; j <= capacity; j++) {
                if (j >= w[i]) {
                    int vWithI = values[j - w[i]] + v[i];
                    int vWithoutI = values[j];
                    newValues[j] = Math.max(vWithI, vWithoutI);
                } else {
                    newValues[j] = values[j];
                }
            }
            values = newValues;
        }
        return values[capacity];
    }
}
