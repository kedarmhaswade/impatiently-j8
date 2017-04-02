package tmp;

import java.util.Random;

/**
 * <p>
 *     Can you write a <i>recursive program </i> to find out the hidden number of a given non-negative number?
 * </p>
 * <p>
 *     Irving Adler gives the following procedure to find the hidden number S, of a number N:
 *     <ol>
 *         <li>Sum its digits and call it S.</li>
 *         <li>If S is greater than 9, N = S. Go back to 1.</li>
 *         <li>Return S.</li>
 *     </ol>
 * </p>
 * Created by kedar on 3/14/17.
 */
public class Hidden {
    public static void main(String[] args) {
        Random r = new Random(System.currentTimeMillis()); // no deterministic behavior needed
        // all are assertions! Run with -ea
        for (int i = 0; i < 100; i++) {
            long n = Math.abs(r.nextLong());
            long rec = hidden(n);
            long iter = hiddenIter(n);
            assert rec == iter + 1 : "don't match for n: " + n + " : rec: " + rec + ", iter: " + iter;
        }
    }

    private static long hidden(long n) {
        if (n < 10)
            return n;
        return hidden(n % 10 + hidden(n / 10));
    }
    private static long hiddenIter(long n) {
        while (n >= 10) {
            int sum = 0;
            do {
                sum += n % 10;
                n /= 10;
            } while (n > 10);
            n += sum;
        }
        return n;
    }
}
