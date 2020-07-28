package practice;


import java.math.BigInteger;

/**
 * Implements John Conway's <a href="www.jstor.org/stable/2690263.">prime producing machine</a> (introduced by Richard Guy).
 *
 * <p>
 *     On my Mac, it takes about 12 minutes to find first 100 primes using this method! <br/>
 *     java -cp target/classes practice.ConwayPrimer  704.86s user 3.82s system 100% cpu 11:45.84 total
 * </p>
 */
public class ConwayPrimer {
    private static class Rational {
        int numerator, denominator;

        Rational(int n, int d) {
            if (d == 0) {
                throw new IllegalArgumentException("divide by zero!");
            }
            this.numerator = n;
            this.denominator = d;
        }

        @Override
        public String toString() {
            return numerator + "/" + denominator;
        }
    }

    private static Rational[] rationals = new Rational[]{
        new Rational(17, 91),
        new Rational(78, 85),
        new Rational(19, 51),
        new Rational(23, 38),
        new Rational(29, 33),
        new Rational(77, 29),
        new Rational(95, 23),
        new Rational(77, 19),
        new Rational(1, 17),
        new Rational(11, 13),
        new Rational(13, 11),
        new Rational(15, 14),
        new Rational(15, 2),
        new Rational(55, 1),
    };

    public static BigInteger next(BigInteger cur) {
        int i = 0;
        while (i < rationals.length) {
            Rational r = rationals[i];
            BigInteger[] qr = cur.divideAndRemainder(BigInteger.valueOf(r.denominator));
            if (qr[1].equals(BigInteger.ZERO)) {
                try {
                    BigInteger n = qr[0].multiply(BigInteger.valueOf(r.numerator));
//                    System.out.println("next:"  + n);
                    return n;
                } catch (Exception e) {
                    System.out.println("rational: " + r + ", cur: " + cur);
                    throw new RuntimeException(e);
                }
            }
            i += 1;
        }
        throw new RuntimeException("unreachable: " + cur);
    }

    public static void produce(int ordinal) {
        BigInteger cur = BigInteger.valueOf(2);
        int nth = 0;
        long step = 0;
        while (nth < ordinal) {
            cur = next(cur);
            step += 1;
            int po2 = (int) whichPowerOf2(cur);
            if (po2 >= 0) { // power of two!
                nth += 1;
//                System.out.println("output power of two: " + cur);
                System.out.println("found prime #" + nth + ": " + po2 + ", steps: " + step);
                step = 0;
            }
        }
    }

    private static long whichPowerOf2(BigInteger cur) {
//        System.out.println("cur: " + cur);
        if (!cur.and(cur.subtract(BigInteger.ONE)).equals(BigInteger.ZERO))
            return -1; // not a power of 2
        int n = cur.bitLength();
        for (int i = 0; i < n; i++) {
            if (cur.testBit(i)) {
                return i;
            }
        }
        throw new AssertionError("how did that happen? cur: " + cur);
    }

    public static void main(String[] args) {
        produce(100);
    }
}
