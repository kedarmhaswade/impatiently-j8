package tmp;

import java.util.Arrays;

/**
 * Created by kmhaswade on 3/4/16.
 */
public class Factorial {
    static int[] fact(int n) {
        int[] r = new int[3];
        r[0] = 1;
        for (int i = 1; i <= n; ++i) {
            int carry = 0;
            for (int j = 0; j < r.length; ++j) {
                int x = r[j] * i + carry;
                r[j] = x % 10;
                carry = x / 10;
                System.out.println(Arrays.toString(r));
            }
        }
        return r;
    }
    static void printFactorial(int[] result) {
        int i = result.length - 1;
        while (i > 0 && result[i] == 0)
            --i;
        while (i >= 0)
            System.out.print(result[i--]);
        System.out.println();
    }
    public static void main(String[] args) {
        printFactorial(fact(5));
    }
}
