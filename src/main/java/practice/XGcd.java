package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Sage-Math function <code>xgcd(a, b)</code> that takes two non negative integers <code>a, b, b ≠ 0</code>
 * and returns three integers, <code>x, y, g</code>, such that,
 * <ul>
 *     <li> g is the greatest common divisor of <code>a, b</code>, i.e. <code>g = gcd(a, b)</code></li>
 *     <li> <code>x, y</code> are such that <code>ax + by = g</code></li>
 * </ul>
 * This function is sometimes called the eXtended GCD algorithm. Note that <code>x, y</code> are not unique. This class
 * implements an algorithm to get one such pair.
 */
public class XGcd {

    /**
     * Returns three integers as documented above.
     *
     * @param a a non negative integer
     * @param b a positive integer
     * @return <code>int[3]</code> as documented above
     */
    public static int[] xgcd(int a, int b) {
        if (a < 0) {
            throw new IllegalArgumentException("a: " + a + " may not be negative");
        }
        if (b <= 0) {
            throw new IllegalArgumentException(("b: " + b + " must be positive"));
        }
        // save a and b for testability/assertions in the code!
        int a0 = a;
        int b0 = b;
        int r, g;
        List<int[]> coefficients = new ArrayList<>();
        do {
            r = a % b;
            if (r == 0) {
                g = b;
                if (g == b0) {
                    coefficients.add(new int[]{0, 1});
                }
                break;
            }
            int[] c = new int[]{1, -a / b};
            assert c[0] * a + c[1] * b == r;
            coefficients.add(c);
            a = b;
            b = r;
        } while (true);
        for (int i = coefficients.size() - 2; i >= 0; i--) {
            int m = coefficients.get(i + 1)[1];
            coefficients.get(i)[0] *= m;
            coefficients.get(i)[1] *= m;
            coefficients.get(i)[1] += coefficients.get(i + 1)[0];
        }
        int x = coefficients.get(0)[0];
        int y = coefficients.get(0)[1];
        assert a0 * x + b0 * y == g; // the so-called Bézout's identity
        return new int[]{x, y, g};
    }
}
