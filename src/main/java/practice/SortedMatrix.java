package practice;

/** See <a href="https://www.careercup.com/question?id=5767203879124992"> here </a> for details.
 * <p>
 *     You are given a matrix with N rows and N columns.
 *     Elements in matrix can be either 1 or 0.
 *     Each row and column of matrix is sorted in ascending order.
 * </p>
 * <p>
 *     Find number of 0-s in the given matrix.
 * </p>
 * <p>
 *     My first attempt is to use recursion and binary search along the main diagonal of the mxn matrix that we
 *     are going to get (m may equal n). I am relatively sure, but not completely sure of its correctness.
 * </p>
 * <p>
 *     Is there another way of looking at it?
 * </p>
 * Created by kmhaswade on 7/7/16.
 */
public class SortedMatrix {

    /**
     * <p>Returns the number of zeros in the sub-matrix of the given matrix that is defined by row indexes r1, r2 and
     * column indexes c1 and c2. Recursively solves the problem by converting a matrix into four matrices, using binary
     * search.
     * </p>
     * <p>
     *     The initial call could be <code> nz(m, 0, m.length - 1, 0, m[0].length - 1)</code>
     * </p>
     * @param matrix the original matrix of 0's and 1's, all rows are sorted and all columns are sorted
     * @param r1 top row index
     * @param r2 bottom row index
     * @param c1 left column index
     * @param c2 right column index
     * @return
     */
    static int nz(int[][] matrix, int r1, int r2, int c1, int c2) {
//        throw new RuntimeException("nyi");
        int acc = 0;
        for (int i = 0; i < matrix.length; i++) {
            int z = nzRow(matrix[i], c1, c2);
            acc += z;
            if (z == 0)
                return acc; // we are done, all 1's in this and subsequent rows
            c2 = c1 + z - 1;
        }
        return acc;
//        // base cases
//        if (r1 < matrix.length && c1 < matrix[r1].length && matrix [r1][c1] == 1)
//            return 0;
//        // somehow we have exceeded the limits
//        if ((r1 > r2) || (c1 > c2))
//            return 0;
//        if (r1 == r2)
//            return nzRow(matrix, r1, c1, c2); // number of zeros in row r1 = r2 between columns [c1, c2]
//        if (c1 == c2)
//            return nzCol(matrix, c1, r1, r2); // number of zeros in col c1 = c2 between rows [r1, r2]
//        // recursive decomposition into four problems
//        int nzmd = nzDiag(matrix, r1, r2, c1, c2); // no of zeros on main diagonal
//        System.out.println("nzmd =  " + nzmd);
//        int midr = r1 + nzmd - 1;
//        int midc = c1 + nzmd - 1;
//        System.out.println("mid indexes: " + midr + " " + midc + ", r1 r2 c1 c2 =  " + r1 + " " + r2  + " " + c1 + " " +
//                "" + c2);
//        assert mid >= r1;
//        int nz1 = (int) Math.pow((midr - r1 + 1), 2); // top-left
//        System.out.println("nz1 =  " + nz1);
//        int nz2 = nz(matrix, r1, mid, mid + 1, c2);      // top-right
//        System.out.println("nz2 = " + nz2);
//        int nz3 = nz(matrix, mid + 1, r2, c1, mid);  // bottom-left
//        System.out.println("nz3 = " + nz3);
//        int nz4 = nz(matrix, mid + 1, r2, mid + 1, c2); // bottom-right
//        System.out.println("nz4 = " + nz4);
//        return nz1 + nz2 + nz3 + nz4;
    }

    public static int nzSlow(int[][] matrix, int r1, int r2, int c1, int c2) {
        int nz = 0;
        for (int i = r1; i <= r2; i++) {
            nz += nzRow(matrix, i, c1, c2);
        }
        return nz;
    }

    public static int nzDiag(int[][] matrix, int r1, int r2, int c1, int c2) {
        int midi;
        int accumulated = 0; // number of zeros accumulated so far
        int minDiff = Math.min((r2 - r1), (c2 - c1));
        r2 = r1 + minDiff;
        c2 = c1 + minDiff;
        // now we have a square as we wanted
//        System.out.println(c1 + ", " + c2 + ", accumulated: " + accumulated);
        while (true) {
            if (c1 > c2 || c1 >= matrix[r1].length)
                return accumulated;
            if (c1 == c2)
                return matrix[r1][c1] == 0 ? accumulated + 1 : accumulated;
            int midir = (r1 + r2) >>> 1;
            int midic = (c1 + c2) >>> 1;
            if (midir > r2 || midic > c2)
                return accumulated;
//            System.out.println("midi: " + midi + ", a[midi]: " + a[midi]);
            int mide = matrix[midir][midic];
            if (mide == 0) {
                accumulated += (midic - c1 + 1);
                r1 = midir + 1;
                c1 = midic + 1;
            } else {
                assert mide == 1;
                r2 = midir - 1;
                c2 = midic - 1;
            }
        }

    }

    public static int nzCol(int[][] matrix, int c, int r1, int r2) {
        int midi;
        int accumulated = 0; // number of zeros accumulated so far
//        System.out.println(c1 + ", " + c2 + ", accumulated: " + accumulated);
        while (true) {
            if (r1 > r2)
                return accumulated;
            if (r1 == r2)
                return matrix[r1][c] == 0 ? accumulated + 1 : accumulated;
            midi = (r1 + r2) >>> 1;
//            System.out.println("midi: " + midi + ", a[midi]: " + a[midi]);
            int mide = matrix[midi][c];
            if (mide == 0) {
                accumulated += (midi - r1 + 1);
                r1 = midi + 1;
            } else {
                assert mide == 1;
                r2 = midi - 1;
            }
//            System.out.println(c1 + ", " + c2 + ", accumulated: " + accumulated);
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
//                br.readLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    private static int nzRow(int[][] matrix, int r, int c1, int c2) {
        return nzRow(matrix[r], c1, c2);
    }
    public static int nzRow(int[] a, int c1, int c2) {
        int midi;
        int accumulated = 0; // number of zeros accumulated so far
//        System.out.println(c1 + ", " + c2 + ", accumulated: " + accumulated);
        while (true) {
            if (c1 > c2 || c1 >= a.length)
                return accumulated;
            if (c1 == c2)
                return a[c1] == 0 ? accumulated + 1 : accumulated;
            midi = (c1 + c2) >>> 1;
//            System.out.println("midi: " + midi + ", a[midi]: " + a[midi]);
            int mide = a[midi];
            if (mide == 0) {
                accumulated += (midi - c1 + 1);
                c1 = midi + 1;
            } else {
                assert mide == 1;
                c2 = midi - 1;
            }
//            System.out.println(c1 + ", " + c2 + ", accumulated: " + accumulated);
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
//                br.readLine();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
