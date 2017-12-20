package practice;

/**
 * <p>
 *     Given an integer n, find the <i> minimum </i> number of squares that sum up to n.
 * </p>
 * <p>
 *     Any integer n can be expressed as a sum of n 1^2. But we're looking for minimum number
 *     of squares. For example, for n = 8, this number is 2 (2^2 + 2^2).
 * </p>
 */
public class SumOfMinSquares {

    public static void main(String[] args) {
        System.out.println(minSquares(80));
    }

    private static int minSquares(int sum) {
        int[] p = new int[sum + 1];
        fill(p, sum);
        if (p[sum] == 1) {
            return 1;
        }
        for (int i = 2; i <= sum; i++) {
            int mini = p[i];
            for (int j = 1; j <= (i >>> 1); j++) {
                mini = Math.min(mini, p[j] + p[i - j]);
            }
            p[i] = mini;
        }
        return p[sum];
    }

    private static void fill(int[] p, int sum) {
        p[0] = 1;
        for (int i = 1; i <= sum; i++) {
                p[i] = i; // n = 1^2 + 1^2 + ... n times
        }
        for (int i = 2, sq; (sq = i * i) <= sum; i++) {
            p[sq] = 1;
        }
    }
}
