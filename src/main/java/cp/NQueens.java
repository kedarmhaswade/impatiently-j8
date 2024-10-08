package cp;

import static cp.Util.*;

/**
 * Uses recursive backtracking to solve the n-queens problem as defined in {@linkplain FourQueens} in the
 * general case.
 */
public class NQueens {

    private static long nWays = 0;
    private static int[][] b;

    public static long numWays(int n) {
        nWays = 0;
        b = new int[n][n];
        solve(n); // return value ignored
        return nWays;
    }

    private static boolean solve(int n) {
        if (n == 0) {
            return true;
        }
        int r = b.length - n; // current r to place a queen on
        for (int i = 0; i < b[r].length; i++) {
            if (isQueenSafeSlow(b, r, i)) {
                b[r][i] = 1;
                if (solve(n - 1)) {
                    nWays += 1;
                }
                b[r][i] = 0;
            }
        }
        return false;
    }
}
