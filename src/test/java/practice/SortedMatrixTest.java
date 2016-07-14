package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.SortedMatrix.*;

/**
 * Created by kmhaswade on 7/11/16.
 */
public class SortedMatrixTest {

    @Test
    public void testAll1Row() {
        int[] a = {1, 1, 1, 1, 1, 1};
        assertEquals(0, nzRow(a, 0, a.length - 1));
        assertEquals(0, nzRow(a, 3, a.length - 1));
    }
    @Test
    public void testAllZeros() {
        int[] a = new int[10_000];
        assertEquals(10_000, nzRow(a, 0, a.length - 1));
    }
    @Test
    public void testManyZeros() {
        int[] a = {0, 1, 1, 1, 1, 1};
        assertEquals(1, nzRow(a, 0, a.length - 1));
        a = new int[] {0, 0, 0, 1, 1, 1};
        assertEquals(3, nzRow(a, 0, a.length - 1));
        a = new int[1000];
        a[998] = a[999] = 1;
        assertEquals(998, nzRow(a, 0, a.length - 1));
        a = new int[1000];
        for (int i = 10; i < 1000; i++)
            a[i] = 1;
        assertEquals(10, nzRow(a, 0, a.length - 1));
        assertEquals(0, nzRow(a, 10, a.length - 1));
        assertEquals(1, nzRow(a, 9, a.length - 1));
    }
    @Test
    public void testAllZeroCol() {
        int[][] matrix = new int[5][1]; // a column matrix
        matrix[0][0] = 0;
        matrix[1][0] = 0;
        matrix[2][0] = 0;
        matrix[3][0] = 0;
        matrix[4][0] = 0;
        assertEquals(5, nzCol(matrix, 0, 0, 4));
    }
    @Test
    public void testAllOneCol() {
        int[][] matrix = new int[5][1]; // a column matrix
        matrix[0][0] = 1;
        matrix[1][0] = 1;
        matrix[2][0] = 1;
        matrix[3][0] = 1;
        matrix[4][0] = 1;
        assertEquals(0, nzCol(matrix, 0, 0, 4));
    }
    @Test
    public void testMixedCol() {
        int MAX = 100;
        int coli = 3;
        int[][] matrix = new int[MAX][4]; // a column matrix
        for (int i = 50; i < MAX; i++) {
            matrix[i][coli] = 1;
        }
        // 50 0's followed by 50 1's in the last column of matrix
        assertEquals(50, nzCol(matrix, coli, 0, MAX - 1));
    }
    @Test
    public void testDiagonal() {
        int[][] m1 = {
                {0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        assertEquals(2, nzDiag(m1, 0, m1.length - 1, 0, m1[0].length - 1));
        int[][] m2 = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1}
        };
        assertEquals(4, nzDiag(m2, 0, m2.length - 1, 0, m2[0].length - 1));
    }
    @Test
    public void testAll1Diag() {
        int[][] m1 = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        assertEquals(0, nzDiag(m1, 0, m1.length - 1, 0, m1[0].length - 1));
    }
    @Test
    public void testNzDiagRect() {
        int[][] rect = {
                {0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 0, 1, 1, 1}
        };
        assertEquals(1, nzDiag(rect, 0, 1, 2, 4)); // note indices
    }
    @Test
    public void all0s() {
        int lim = 1000;
        int[][] m1 = new int[lim][lim];
        assertEquals(lim, nzDiag(m1, 0, m1.length - 1, 0, m1[0].length - 1));
    }
    @Test
    public void smallMatrix() {
        int[][] sm = {
                {0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        assertEquals(7, nz(sm, 0, sm.length - 1, 0, sm[0].length - 1));
        assertEquals(7, nzSlow(sm, 0, sm.length - 1, 0, sm[0].length - 1));
    }
    @Test
    public void zeroMatrix() {
        int[][] sm = {
                {0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        assertEquals(6, nz(sm, 0, 1, 0, 1));
//        assertEquals(7, nzSlow(sm, 0, sm.length - 1, 0, sm[0].length - 1));
    }
    @Test
    public void smallRect1() {
        int[][] sm = {
                {0, 0, 0, 1, 1},
                {0, 0, 1, 1, 1},
                {0, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        assertEquals(1, nz(sm, 0, 1, 2, 4));
//        assertEquals(1, nzSlow(sm, 0, 1, 2, 4));

    }
    @Test
    public void smallRect2() {
        int[][] sm = {
                {0, 1, 1},
                {1, 1, 1}
        };
        assertEquals(1, nz(sm, 0, sm.length - 1, 0, sm[0].length - 1));
//        assertEquals(7, nzSlow(sm, 0, sm.length - 1, 0, sm[0].length - 1));

    }
    @Test
    public void oneMatrix() {
        int[][] sm = {
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        };
        assertEquals(0, nz(sm, 0, sm.length - 1, 0, sm[0].length - 1));
//        assertEquals(7, nzSlow(sm, 0, sm.length - 1, 0, sm[0].length - 1));
    }
    @Test
    public void bigMatrix1() {
        int n = 1000;
        int[][] bm = new int[n][n];
        for (int j = 0; j < n; j++)
            for (int i = n - 1; i >= n - j; i--)
                bm[j][i] = 1;
        int expnz = (n * (n + 1)) / 2;
//        System.out.println(Arrays.deepToString(bm));
        assertEquals(expnz, nz(bm, 0, n - 1, 0, n - 1));
    }
    @Test
    public void bigMatrix2() {
        int n = 1000;
        int[][] bm = new int[n][n];
        // only two rows of zeros
        for (int j = 2; j < n; j++)
            for (int i = 0; i < n; i++)
                bm[j][i] = 1;
        int expnz = n * 2;
//        System.out.println(Arrays.deepToString(bm));
        assertEquals(expnz, nz(bm, 0, n - 1, 0, n - 1));
    }

}