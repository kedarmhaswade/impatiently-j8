package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * <p>
 * Implements the Sylvester algorithm to find a given fraction as a sum of unit fractions. The implementation is
 * rudimentary. James Joseph Sylvester found this algorithm in the 18th century.
 * </p>
 */
public class Sylvester {

    static List<Long> ufs(long n, long d) {
        List<Long> units = new ArrayList<>(4);
        do {
            long gcd = euclid(d, n); // euclid(n, d) is okay too, perhaps marginally slower
            n /= gcd;
            d /= gcd;
            // now n and d are relatively prime
            if (n == 1) {
                break;
            }
            long u = (d / n) + 1; // greatest unit fraction smaller than the given fraction
            units.add(u);
            long g = euclid(d, u);
            long lcm = Math.multiplyExact(g, Math.multiplyExact(d / g, u / g)); // bail - possibly inevitable overflow
            n = (lcm / d) * n - (lcm / u);
            d = lcm;
        } while (true);
        units.add(d);
        return units;
    }

    static long euclid(long a, long b, long... xs) {
        return chain2(Sylvester::euclid, a, b, xs);
    }

    static long lcm(long a, long b, long... xs) {
        return chain2(Sylvester::lcm, a, b, xs);
    }

    static boolean verify(long n, long d, List<Long> units) {
        if (units.isEmpty()) {
            return false;
        }
        if (units.size() == 1) {
            return d == n * units.get(0);
        }
        long[] rem = units.subList(2, units.size()).stream().mapToLong(Long::longValue).toArray();
        long lcm = lcm(units.get(0), units.get(1), rem);
        long lhs = (lcm / d) * n;
        long rhs = 0L;
        for (Long unit : units) {
            rhs += lcm / unit;
        }
        return lhs == rhs;
    }

    static String toStr(long n, long d, List<Long> ufs) {
        return format("%d/%d = ", n, d) + ufs.stream().map(e -> format("1/%d", e)).collect(Collectors.joining(" + "));
    }

    // PRIVATE METHODS

    private static long euclid(long a, long b) {
        while (b > 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }

    private static long chain2(BiFunction<Long, Long, Long> f2, long a, long b, long... xs) {
        long r = f2.apply(a, b);
        for (long x : xs) {
            r = f2.apply(x, r);
        }
        return r;
    }

    private static long lcm(long a, long b) {
        long gcd = euclid(a, b);
        return Math.multiplyExact(gcd, Math.multiplyExact(a / gcd, b / gcd));
    }

}
