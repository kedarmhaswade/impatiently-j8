package practice;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static practice.Subsets.getPowersetIteratively;

/**
 * This is the UKMT Problem of the Day 57:
 * <p>
 * Find a set of Fibonacci numbers that among them contain each of the ten digits [0-9]
 * exactly once.
 * </p>
 */
public class FibSet {

    public static void main(String[] args) {

        Set<Set<Integer>> ps = getPowersetIteratively(Set.of(
            1,
            2,
            3,
            5,
            8,
            13,
            21,
            34,
            55,
            89,
            144,
            233,
            377,
            610,
            987//,
//            1597,
//            2584,
//            4181,
//            6765
        ));
        for (Set<Integer> s : ps) {
            if (hasUniqueDigitsOnce(s)) {
                System.out.println("found: " + s);
            }
        }
    }

    private static boolean hasUniqueDigitsOnce(Set<Integer> s) {
        Set<Integer> digits = new HashSet<>(10);
        for (int e : s) {
            Set<Integer> intDigits = getDigits(e);
            if (intDigits.isEmpty()) {
                return false;
            }
            for (int d : intDigits) {
                if (!digits.contains(d)) {
                    boolean b = digits.add(d);
                    assert b : "could not add: " + d;
                } else
                    return false;
            }
        }
        return digits.size() == 10;

    }

    private static Set<Integer> getDigits(int e) {
        Set<Integer> digits = new HashSet<>(10);
//        int ee = e;
        do {
            int d = e % 10;
            if (digits.contains(d)) {
//                System.out.println("returning empty set for: " + ee);
                return Collections.emptySet();
            }
            boolean b = digits.add(d);
            assert b : "digit should have been added: " + d;
            e /= 10;
        } while (e != 0);
        return digits;
    }
}
