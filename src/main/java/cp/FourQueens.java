package cp;

import java.util.Arrays;

import static cp.Util.isQueenSafe;

/**
 * Solves the "n-queens problem" iteratively (very naive!) for four queens. The statement of the problem:
 * <pre>
 *     Calculate the number of ways n queens can be placed on an nxn chessboard so that no two queens
 *     attack each other.
 * </pre>
 */
public class FourQueens {

    private static final int N = 4;
    static final int[][] b = new int[4][4]; // package-private

    public static long solve() {
        long nWays = 0;
        for (int i = 0; i < N; i++) { // row #0
            b[0][i] = 1;
            for (int j = 0; j < N; j++) { // row #1
                if (!isQueenSafe(b, 1, j)) {
                    continue;
                }
                b[1][j] = 1;
                for (int k = 0; k < N; k++) { // row #2
                    if (!isQueenSafe(b, 2, k)) {
                        continue;
                    }
                    b[2][k] = 1;
                    for (int l = 0; l < N; l++) { // row #3
                        if (!isQueenSafe(b, 3, l)) {
                            continue;
                        }
                        b[3][l] = 1;
                        nWays += 1;
//                        print(b);
                        b[3][l] = 0;
                    }
                    b[2][k] = 0;
                }
                b[1][j] = 0;
            }
            b[0][i] = 0;
        }
        return nWays;
    }

    private static void print(int[][] b) {
        System.out.println();
        for (int[] row : b)
            System.out.println(Arrays.toString(row));
    }

    private static void clear(int[][] b) {
        for (int[] row : b) {
            Arrays.fill(row, 0);
        }
    }
}
