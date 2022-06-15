package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Finds the number of k-subsets of an n-set (n choose k, in other words).
 * Uses O(k) space and tries to make sure we have some defense against the integer overflow, by using addition
 * rather than multiplication and division.
 * </p>
 * Created by kmhaswade on 8/23/16.
 */
public class NChooseK {

    static long nck(final int n, int k) {
        if (k > n || n <= 0)
            throw new IllegalArgumentException();
        k = Math.min(k, (n - k));
        if (k == 0)
            return 1;
        if (k == 1)
            return n;
        List<Long> list = new ArrayList<>();
        list.add(2L);
        for (int i = 3; i <= n; i++) {
            long first = 1L;
            int j = 1;
            List<Long> list1 = new ArrayList<>();
            for (Long e : list) {
                long ne = e + first;
                if (j == k && i == n)
                    return ne;
                list1.add(ne);
                first = e;
                j += 1;
            }
            list1.add(first + 1);
            list = list1;
        }
        throw new AssertionError();
    }

    /**
     * The method {@linkplain #nck(int, int)} formulates the k-subsets of an n-set in order to find
     * the number of k-subsets. That is unnecessary. We can simply use the formula for n choose k. This method
     * implements that.
     *
     * @param n
     * @param k
     * @return the value of n choose k
     */
    static long nckFast(final int n, int k) {
        if (k > n || n <= 0)
            throw new IllegalArgumentException();
        k = Math.min(k, (n - k));
        long result = 1L;
        for (int i = 1; i <= k; i++) {
            result *= n - i + 1;
            result /= i;
        }
        return result;
    }
}
