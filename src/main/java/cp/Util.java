package cp;

/**
 * Some package-specific utilities.
 */
class Util {
    /**
     * Determines naively if a queen is attacked at rth row and cth column on the given chessboard b.
     * Uses constant space and O(n) time. More time-efficient search is possible. Does not modify b in any way.
     *
     * @param b a two-dimensional array that must be square
     * @param r
     * @param c
     * @return boolean true if a queen at b[r][c] would be attacked. Does not do any bound checking!
     */
    static boolean isQueenSafeSlow(int[][] b, int r, int c) {
        return isQueenSafeRow(b, r) && isQueenSafeColumn(b, c) && isQueenSafeDiag(b, r, c);
    }

    private static boolean isQueenSafeDiag(int[][] b, int r, int c) {
        return isQueenSafeDiagNE(b, r, c) &&
            isQueenSafeDiagSE(b, r, c) &&
            isQueenSafeDiagSW(b, r, c) &&
            isQueenSafeDiagNW(b, r, c);

    }

    private static boolean isQueenSafeDiagNW(int[][] b, int r, int c) {
        int i = r;
        int j = c;
        while (i >= 0 && j >= 0) {
            if (b[i][j] == 1) {
                return false;
            }
            i -= 1;
            j -= 1;
        }
        return true;
    }

    private static boolean isQueenSafeDiagSW(int[][] b, int r, int c) {
        int i = r;
        int j = c;
        while (i < b.length && j >= 0) {
            if (b[i][j] == 1) {
                return false;
            }
            i += 1;
            j -= 1;
        }
        return true;
    }

    private static boolean isQueenSafeDiagSE(int[][] b, int r, int c) {
        int i = r;
        int j = c;
        while (i < b.length && j < b[0].length) {
            if (b[i][j] == 1) {
                return false;
            }
            i += 1;
            j += 1;
        }
        return true;
    }

    private static boolean isQueenSafeDiagNE(int[][] b, int r, int c) {
        int i = r;
        int j = c;
        while (i >= 0 && j < b[0].length) {
            if (b[i][j] == 1) {
                return false;
            }
            i -= 1;
            j += 1;
        }
        return true;
    }

    private static boolean isQueenSafeColumn(int[][] b, int c) {
        for (int[] row : b) { // enumerate rows in the cth column
            if (row[c] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isQueenSafeRow(int[][] b, int r) {
        for (int i = 0; i < b[r].length; i++) { // enumerate columns in the rth row
            if (b[r][i] == 1) {
                return false;
            }
        }
        return true;
    }
}
