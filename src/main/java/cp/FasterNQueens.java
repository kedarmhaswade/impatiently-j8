package cp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static cp.Util.isQueenSafeSlow;

/**
 * Uses recursive backtracking to solve the n-queens problem as defined in {@linkplain FourQueens} in the
 * general case. It uses tricks to speed up the execution of a "naive" implementation.
 */
public class FasterNQueens {
    private static int[][] b;
    private static int[] cols;
    private static final Set<Integer> d1 = new HashSet<>();
    private static final Set<Integer> d2 = new HashSet<>();

    public static long numWays(int n) {
        b = new int[n][n];
        cols = new int[n];
        return solve(n);
    }

    private static long solve(int n) {
        if (n == 0) {
            return 1;
        }
        long nWays = 0L;
        int r = b.length - n; // current r to place a queen on
        for (int i = 0; i < b[r].length; i++) {
            if (cols[i] == 0 && !d1.contains(r + i) && !d2.contains(i - r)) {
                place(r, i, true); // occupy
                nWays += solve(n - 1);
                place(r, i, false); // backtrack
            }
        }
        return nWays;
    }

    private static void place(int row, int col, boolean place) {
        if (place) {
            b[row][col] = 1;
            cols[col] = 1;
            d1.add(row + col);
            d2.add(col - row);
        } else {
            b[row][col] = 0;
            cols[col] = 0;
            d1.remove(row + col);
            d2.remove(col - row);
        }
    }

    public static void main(String[] args) {
        int n = Integer.valueOf(args[0]);
        System.out.println("n = " + n);
        System.out.println("nWays = " + numWays(n));
    }
}
