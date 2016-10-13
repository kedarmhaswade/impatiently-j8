package practice.hr;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Some processing of the stdin/stdout. HR should have provided these routines.
 * Created by kedar on 1/10/16.
 */
public class StdIoUtil {

    /**
     * <p>
     *     Processes two lines, first line has the number of ints appearing on the second line.
     * </p>
     * @param in
     * @return
     * @throws IOException
     */
    static int[] processTwo(InputStream in) throws IOException {
        Scanner sc = new Scanner(in);
        int n = Integer.parseInt(sc.nextLine());
        int[] input = new int[n];
        int i = 0;
        while (i < n && sc.hasNextInt())
            input[i++] = sc.nextInt();
        return input;
    }
}
