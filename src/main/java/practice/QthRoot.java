package practice;

import java.util.function.BiConsumer;

/**
 * Finds the approximate qth root of p, p and q are both natural numbers. A generalization of an inductive
 * construction of finding the √2 from Leo Zippin's book, Uses of Infinity (MAA Press).
 */
public class QthRoot {

    static void qthRootOfP(long p, int q, long m, long n, int iter, BiConsumer<Long, Long> c) {
        if (iter == 1) {
            return;
        }
        long num = pow(m, q) + Math.multiplyExact(p, pow(n, q));
        long den = Math.multiplyExact(2, Math.multiplyExact(pow(m, q - 1), n));
        long g = Sylvester.euclid(num, den);
        num /= g;
        den /= g;
        c.accept(num, den);
        qthRootOfP(p, q, num, den, iter - 1, c);
    }

    static long pow(long n, int p) {
        if (p < 0) {
            throw new IllegalArgumentException("power: " + p + " must be >= 0");
        }
        if (p == 0) {
            return 1L;
        }
        if (p == 1) {
            return n;
        }
        long h = pow(n, p / 2);
        if (p % 2 == 0) {
            return Math.multiplyExact(h, h);
        } else {
            return Math.multiplyExact(n, Math.multiplyExact(h, h));
        }
    }
}
