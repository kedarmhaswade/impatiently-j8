package practice;

/**
 * <p>
 *     A few problems concerning spiral ordering of a 2-D array.
 * </p>
 * Created by kmhaswade on 9/4/16.
 */
public class SpiralOrdering {

    /**
     * Given a spiral order <code>spiral</code> of a matrix and a shift, constructs the matrix that would
     * give that spiral order.
     * @param spiral the spiral order; it must contain n^2 elements if nxn is the order of the matrix
     * @param shift direction array as a two dimensional array
     * @return the matrix whose spiral order is spiral
     */
    static int[][] to2D(int[] spiral, int[][] shift) {
        // ensure lengths
        int length = spiral.length;
        int n = (int) Math.sqrt(length);
        int[][] matrix = new int[n][n];
        int r = 0, c = 0, dir = 0;
        for (int e : spiral) {
            matrix[r][c] = e;
            int rnew = r + shift[dir][0];
            int cnew = c + shift[dir][1];
            if (rnew >= n || rnew < 0 || cnew >= n || cnew < 0 || matrix[rnew][cnew] != 0) {
                dir = (dir + 1) % 4; // change the direction
            }
            r += shift[dir][0];
            c += shift[dir][1];
        }
        return matrix;
    }
    static int[][] to2DClockwise(int[] spiral) {
        return to2D(spiral, new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}});
    }
}
