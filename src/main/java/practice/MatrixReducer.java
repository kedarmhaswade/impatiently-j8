package practice;

import java.util.Arrays;
import java.util.Random;

/** <p>
 * For a matrix of all natural numbers, in any one step, you can either double each element of any one row or subtract
 * one from each element of any one column. Given an 8x8 matrix, can you reduce all its elements to 0's using above
 * procedure? What is the complexity of your algorithm?
 * </p>
 * <p>
 *     Update: This class implements a slow algorithm that I am going to improve upon.
 * </p>
 * Created by kmhaswade on 4/18/16.
 */
public class MatrixReducer {
    public static void main(String[] args) {
        new MatrixReducer(4, 20).solve();
    }
    private final long[][] matrix;
    private final int dim;
    MatrixReducer(int dim, int limit) {
        Random r = new Random(); // System.currentTimeMillis()
        this.dim = dim;
        matrix = new long[dim][dim];
        for (int i = 0; i < dim; i += 1)
            for (int j = 0; j < dim; j += 1) {
                int x;
                do {
                    x = r.nextInt(limit);
                } while (x == 0);
                matrix[i][j] = x;
            }
        print(matrix);
    }
    public void solve() {
        int cols = matrix[0].length;
        for (int i = 0; i < cols; i += 1) {
            System.out.println("start with column number: " + i);
            while (true) {
                makeSmallest1(i);
                print(matrix);
                if (areAll1(i))
                    break;
                double1s(i);
                print(matrix);
            }
            reduceToZero(i);
            print(matrix);
        }
        print(matrix);
    }

    private static void print(long[][] matrix) {
        for (int i = 0; i < matrix.length; i += 1)
            System.out.println(Arrays.toString(matrix[i]));
    }

    private boolean areAll(int col, int val) {
        for (int i = 0; i < dim; i += 1)
            if (matrix[i][col] != val)
                return false;
        return true;
    }
    private boolean areAll1(int col) {
        return areAll(col, 1);
    }
    private void makeSmallest1(int col) {
        long smallest = matrix[0][col];
        for (int i = 1; i < dim; i += 1) {
            long e = matrix[i][col];
            if (e < smallest)
                smallest = e;
        }
        long reduceBy = smallest - 1;
        for (int i = 0; i < dim; i += 1)
            matrix[i][col] -= reduceBy;
        System.out.println("make smallest element i.e. " + smallest + " 1, by reducing every element by: " + reduceBy);
    }
    private void reduceToZero(int col) {
        long e = matrix[0][col];
        System.out.println("reduce: " + e + " to 0");
        for (int i = 0; i < dim; i += 1) {
            assert e == matrix[i][col];
            matrix[i][col] -= e;
        }
    }
    private void double1s(int col) {
        int d = nTimesToDoubleOnes(col);
        for (int i = 0; i < dim; i += 1) {
            if (matrix[i][col] == 1) {
                for (int j = col; j < dim; j += 1) {
                    matrix[i][j] <<= d;
                }
            }
        }
    }
    private int nTimesToDoubleOnes(int col) {
        assert col < dim && dim >= 0;
        long g2 = smallestGreaterThan1(col);
        assert g2 > 1;
        long i = 1;
        int times = 0;
        while (true) {
            i <<= 1;
            times += 1;
            if (i > g2) {
                times -= 1;
                break;
            }
        }
        System.out.println("double 1's up to: " + g2 + " by doubling: " + times + " times");
        return times;
    }

    private long smallestGreaterThan1(int col) {
        assert col < dim && dim >= 0;
        long smallest = Integer.MAX_VALUE;
        for (int i = 0; i < dim; i += 1) {
            long e = matrix[i][col];
            assert e >= 1;
            if (e < smallest && e > 1)
                smallest = e;
        }
        return smallest;
    }
}
