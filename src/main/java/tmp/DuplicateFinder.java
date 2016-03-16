package tmp;

import java.io.IOException;
import java.util.Arrays;

/** Trying out the solution to:
 *  http://stackoverflow.com/questions/5739024/finding-duplicates-in-on-time-and-o1-space
 * Created by kmhaswade on 3/5/16.
 */
public class DuplicateFinder {
    public static void main(String[] args) throws IOException {
        int[] a = new int[]{3, 0, 1, 3, 2};
        int n = a.length;
        for (int i = 0; i <= n-1; i++) {
            System.out.println("i: " + i + ", a[i]: " + a[i] + ", a[a[i]]: " + a[a[i]]);
            while (a[a[i]] != a[i]) {
                int tmp = a[i];
                a[i] = a[a[i]];
                a[tmp] = tmp;
                System.out.println(Arrays.toString(a));
                //System.in.read();
            }
        }
        for (int i = 0; i <= n-1; i++)
            if (a[i] != i)
                System.out.println(a[i]);
    }
}
