package practice.euler;

import java.util.*;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * <p>
 * What is the largest prime factor of the number 600851475143?
 */
public class LargestPrimeFactor {
    static final char COMPOSITE = 'c';
    static final char PRIME = 'p';
    static final char NEITHER = '\0';

    static long calculate(long n) {
        char[] sieve = sieve((int) Math.ceil(Math.sqrt(n)));
//        long lpf = largestRecursive(n, sieve, 2);
        long lpf = largestIterative(n, sieve, 2, new HashMap<>());
        if (lpf == 1) {
            return n; // n is PRIME
        }
        return lpf;
    }

    static long largestRecursive(long n, char[] sieve, int startingPrime) {
        if (n % startingPrime == 0) {
            long other = n / startingPrime;
            if (other == startingPrime)
                return startingPrime;
            long otherLargest = largestRecursive(other, sieve, startingPrime);
            return startingPrime > otherLargest ? startingPrime : otherLargest;
        }
        int nextPrime = next(sieve, startingPrime, PRIME);
        if (nextPrime == -1)
            return n;
        return largestRecursive(n, sieve, nextPrime);
    }

    static long largestIterative(long n, char[] sieve, int startingPrime, Map<Long, Integer> pfs) {
        while (true) {
            if (n % startingPrime == 0) {
//                System.out.println("prime factor: " + startingPrime);
                pfs.merge((long) startingPrime, 1, Integer::sum);
                long other = n / startingPrime;
                if (startingPrime > other)
                    return startingPrime;
                if (startingPrime == other) {
                    pfs.merge((long) startingPrime, 1, Integer::sum);
                    return startingPrime;
                }
                n = other;
            } else {
                startingPrime = next(sieve, startingPrime, PRIME);
                if (startingPrime == -1) {
                    pfs.merge(n, 1, Integer::sum);
                    return n;
                }
            }
        }
    }

    public static void main(String[] args) {
//        char[] sieve = sieve(1234);
//        printPrimes(sieve);
        System.out.println(calculate(26));
        System.out.println(calculate(658));
        System.out.println(calculate(10_000_851_475_143L));
        System.out.println(calculate(600_851_475_143L));
//        System.out.println(calculate(26248954003L));
    }

    static void printPrimes(char[] sieve) {
        for (int i = 2; i < sieve.length; i++) {
            if (sieve[i] == PRIME)
                System.out.println(i);
        }
    }

    static char[] sieve(int limit) {
        char[] s = new char[limit + 1];
        init(s, NEITHER);
        int i = 2;
        while (i <= limit) {
            s[i] = PRIME;
            fillComposites(s, i);
            i = next(s, i, NEITHER);
            if (i == -1) { // there is no next PRIME
                return s;
            }
        }
        return s;
    }

    static int next(char[] s, int startIndex, char what) {
        int j = startIndex + 1; // start with next index
        while (j < s.length) {
            if (s[j] == what)
                return j;
            j += 1;
        }
        return -1;
    }

    private static void fillComposites(char[] s, int i) {
        // fills s with multiples of i from i*i onward
        int j = i;
        int multiple;
        while (true) {
            try {
                multiple = Math.multiplyExact(i, j);
                s[multiple] = COMPOSITE;
                j += 1;
            } catch (Exception e) {
                //overflow, ignore // 146543 * 146543 = 14369
                return;
            }
        }

    }

    private static void init(char[] s, char neither) {
        Arrays.fill(s, neither);
    }
}
