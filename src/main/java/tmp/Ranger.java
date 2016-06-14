package tmp;

import java.io.*;

/** Given a  stream of strings that represent _sorted_ numbers, finds the ranges of contiguous numbers from them.
 * <p>
 *     For example, given:
 *     12
 *     13
 *     15
 *     20
 *     21
 *     22
 *     should print:
 *     12..13, 15, 20..22
 * </p>
 * Created by kmhaswade on 6/10/16.
 */
public class Ranger {
    public static final class IntegerInterval {
        private final long low;
        private final long high;
        public IntegerInterval(long low, long high) {
            if (low > high)
                throw new IllegalArgumentException("low: " + low + " may not be higher than high: " + high);
            this.low = low;
            this.high = high;
        }
        @Override
        public String toString() {
            if (low == high)
                return "" + low;
            return low + ".." + high;
        }
    }
    public static void ranges(InputStreamReader r) throws IOException {
        BufferedReader reader = new BufferedReader(r);
        String line;
        boolean extend = false;
        long low = Long.MIN_VALUE, prev = -1;
        long curr = -1;
        while ((line = reader.readLine()) != null) {
            curr = Long.parseLong(line);
            if (extend) {
                assert low != Long.MIN_VALUE;
                if (curr == prev + 1) {
                    extend = true;
                } else {
                    System.out.println(new IntegerInterval(low, prev));
                    low = curr;
                    extend = true;
                }
            } else {
                low = curr;
                extend = true;
            }
            prev = curr;
        }
        if (extend) {
            System.out.println(new IntegerInterval(low, curr));
        }
    }

    public static void main(String[] args) throws IOException {
//        String str = "0\n1\n2\n3\n4\n6\n8\n9\n100\n";
//        ByteArrayInputStream bais = new ByteArrayInputStream(str.getBytes());
//        ranges(new InputStreamReader(bais));
        ranges(new InputStreamReader(System.in));
    }
}
