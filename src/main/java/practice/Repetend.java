package practice;

import java.util.*;

import static java.util.Collections.emptyList;

/**
 * Anand Rangarajan asked a very interesting problem that has to do with "repetends" in a decimal fraction.
 */
public class Repetend {
    /**
     * A value class that represents the non repeating and repeating parts of a decimal fraction.
     */
    static final class Decimal {
        final private List<Integer> nr;
        final private List<Integer> r;

        public Decimal(List<Integer> nr, List<Integer> r) {
            this.nr = new ArrayList<>(nr);
            this.r = new ArrayList<>(r);
        }
        public List<Integer> rep() {
            return Collections.unmodifiableList(r);
        }
        public List<Integer> nrep() {
            return Collections.unmodifiableList(nr);
        }
        @Override
        public String toString() {
            return "0." + flatten(nr) + "[" + flatten(r) + "]";
        }
        String repString() {
            return flatten(r);
        }
        String nonRepString() {
            return flatten(nr);
        }
        private static String flatten(List<Integer> xs) {
            StringBuilder sb = new StringBuilder(xs.size());
            for (int i : xs) {
                sb.append(i);
            }
            return sb.toString();
        }
    }

    /**
     * Returns the length of the repetend of the fraction n/d. The repetend of a terminating fraction is of 0 length.
     * See <a href="https://en.wikipedia.org/wiki/Repeating_decimal">Repeating Decimal</a> for details.
     *
     * @param n a positive integer
     * @param d a positive integer
     * @return Decimal that represents the decimal part of the fraction formed by the given numerator and denominator.
     */
    static Decimal repetend(int n, int d) {
        // no check for non positive integers
        if (n >= d) {
            n %= d;
        }
        // n < d -- it's a proper fraction now
        if (n == 0) {
            return new Decimal(emptyList(), emptyList());
        }
        List<Integer> qs = new ArrayList<>(d - 1);
        Map<Integer, Integer> rqMap = new HashMap<>();
        int key = -1;
        while (true) {
            int r = n;
            if (r == 0) { // terminating decimal
                return new Decimal(qs, emptyList());
            }
            n *= 10;
            int q = n / d;
            if (rqMap.containsKey(r)) {
                key = r;
                break;
            }
            rqMap.put(r, q);
            qs.add(q); // ignore return value
            n %= d;
        }
        assert key != -1;
//        System.out.println("Look for q: " + rqMap.get(key) + " in the list to split");
        int keyIndex = qs.indexOf(rqMap.get(key));
        assert keyIndex != -1 : "assertion failure, key: " + key + " does not exist in the list of quotients";
        return new Decimal(qs.subList(0, keyIndex), qs.subList(keyIndex, qs.size()));
    }
}
