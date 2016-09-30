package practice.abb;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 *     Given a matrix whose elements are specified on the stdin in a specific way,
 *     <ul>
 *         <li> Construct the matrix</li>
 *         <li> Traverse it spirally from top-left in a clockwise manner</li>
 *         <li> Output the elements visited on the way</li>
 *     </ul>
 * </p>
 * <p>
 *     This is asked on Hackerrank (argh) and I am going to use my IDE to do a worthwhile implementation.<br/>
 *     &lt;rant><br/>
 *     It's of course insane to test the programs this way where most of the work is in input/output
 *     processing! The right way to do this is, of course, to use proper tests based on contracts of
 *     methods that code the core of the problem. Alas.<br/>
 *     &lt;/rant>
 * </p>
 * <p>
 *     The idea is to use direction change at specified locations. Of course, when you run out of bounds,
 *     you change the direction. But the trick here is that
 * </p>
 * Created by kmhaswade on 9/25/16.
 */
public class SpiralMatrixSolution {
// first problem: I need to name my classes in a completely unintuitive way for hackerrank to
// validate my solutions.

    public static void main(String[] args) {
        List<List<String>> matrix = processInput(System.in);
        SpiralMatrix spiral = new SpiralMatrix(matrix);
        spiral.walk(x -> System.out.print(x));
    }

    static class SpiralMatrix {
        private final List<List<String>> matrix; // ok to stringify for a general solution
        SpiralMatrix(List<List<String>> matrix) {
            this.matrix = matrix;
        }

        /**
         * Performs the spiral walk of the given matrix. Instead of storing the elements, employs a
         * streaming API such that, as the new element is visited, it is sent to a {@linkplain Consumer}.
         * <p>
         *     Note: Mutates the given {@linkplain #matrix}!
         * </p>
         * It is guaranteed that exactly <code>matrix.rows x matrix.cols</code> elements
         * will be sent by this method to the given consumer.
         * @param consumer a string consumer that accepts a string that is the matrix's element.
         */
        public void walk(Consumer<String> consumer) {
            int rows = matrix.size();
            int cols = matrix.get(0).size();
            int n = rows * cols;
            int[][] dir = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            int r = 0, c = 0, dirIndex = 0, dirLen = dir.length;
            for (int i = 0; i < n; i++) {
                consumer.accept(matrix.get(r).get(c));
                matrix.get(r).set(c, null); // another sentinel
                int nr = r + dir[dirIndex][0];
                int nc = c + dir[dirIndex][1];
                if (nr == rows || nc == cols || nr < 0 || nc < 0 || matrix.get(nr).get(nc) == null) {
                    dirIndex = (dirIndex + 1) % dirLen;
                    r += dir[dirIndex][0];
                    c += dir[dirIndex][1];
                } else {
                    r = nr;
                    c = nc;
                }
            }
        }
    }

     static List<List<String>> processInput(InputStream is) {
        Scanner scanner = new Scanner(is);
        String[] rc = scanner.nextLine().split("\\s*,\\s*");
        int rows = Integer.parseInt(rc[0]);
        List<List<String>> matrix = new ArrayList<>(rows);
//        int cols = Integer.parseInt(rc[1]); // to verify, maybe?
        IntStream.range(0, rows).forEach(i -> matrix.add(stream(scanner.nextLine().split("\\s*,\\s*")).collect(toList())));
        return matrix;
    }
}
