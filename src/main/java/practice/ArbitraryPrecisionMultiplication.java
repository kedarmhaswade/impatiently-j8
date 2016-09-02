package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 *     Given two decimal numbers as a list of digits (LSD being the last entry in the list, first element carrying
 *     the sign of the entire number), multiply them.
 * </p>
 * Created by kmhaswade on 8/31/16.
 */
public class ArbitraryPrecisionMultiplication {

    static List<Integer> multiply(List<Integer> a, List<Integer> b) {
        boolean positive = (a.get(0) > 0 && b.get(0) > 0) || (a.get(0) < 0 && b.get(0) < 0);
        int alen = a.size(), blen = b.size();
        List<Integer> c = new ArrayList<>(Collections.nCopies(alen + blen, 0));
        int start = 0;
        for (int i = blen - 1; i >= 0; i --) {
            for (int j = alen - 1, k = 0; j >= 0; j--, k++) {
                c.set(start + k, c.get(start + k) + (Math.abs(a.get(j)) * Math.abs(b.get(i))));
            }
            start += 1;
        }
        int carry = 0;
        int i = 0;
        for (; c.get(i) != 0; i++) {
            Integer x = c.get(i) + carry;
            c.set(i, x % 10);
            carry = x / 10;
        }
        if (carry != 0)
            c.set(i, positive ? carry : -carry);
        Collections.reverse(c);
        return c;
    }
}
