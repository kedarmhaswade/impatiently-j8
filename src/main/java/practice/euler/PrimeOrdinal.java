package practice.euler;

import static practice.euler.LargestPrimeFactor.*;

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13,
 * we can see that the 6th prime is 13.
 * <p>
 * What is the 10,001st prime number?
 */
public class PrimeOrdinal {
    // A naive algorithm is to construct the sieve by guessing
    public static void main(String[] args) {
//        System.out.println(" hence, " + primeSlow(6));
//        System.out.println("nth Prime: " + nthPrime(6));
        int nth = 200_000;
        System.out.println(primeSlow(nth));
        System.out.println(nthPrime(nth));
//        for (int i = 15_000; i < 20_000; i++) {
//            long slow = primeSlow(i);
//            long fast = nthPrime(i);
//            if (slow != fast) {
//                System.out.println("nth: " + i + ", " + slow + ", " + fast);
//                break;
//            }
//        }
    }

    /**
     * returns the nth prime number, using a naive and slow algorithm that also needs lots
     * of memory, especially for large numbers
     */
    static long primeSlow(int nth) {
        int pc = 0; // prime counter
        int limit = 203909;
        int primeNo, prev = 2;
        while (true) {
            char[] sieve = sieve(limit);
            primeNo = prev;
            for (; primeNo < sieve.length; primeNo++) {
                if (sieve[primeNo] == PRIME) {
//                    System.out.print(primeNo + ", ");
                    pc += 1;
                    if (pc == nth) {
                        return primeNo;
                    }
                }
            }
            limit *= 2;
            prev = sieve.length;
        }
    }

    /**
     * <p>
     * Returns if a given number is prime. This is perhaps a slow algorithm, but requires constant memory. I discovered
     * this on Project Euler.
     * </p>
     * <p>
     * <li> Use a corollary that all prime numbers greater than 3 can be expressed as 6*k+/-1. This will involve
     * checking divisibility of n by many non prime numbers (e.g. 35), but that is okay for a naive algorithm.</li>
     * </p>
     *
     * @return <code>-1</code> is the number is prime, otherwise the first prime factor of the argument
     * <s> <code>true</code> if the given positive integer is prime, <code>false</code> otherwise </s>
     */
    public static long isPrime(long n) {
        if (n % 2 == 0)
            return 2L;
        if (n % 3 == 0)
            return 3L;
        long p = 5;
        long r = (long) Math.sqrt(n);
        while (p <= r) {
            if ((n % p == 0) || (n % (p + 2) == 0)) {
                return p;
            }
            p += 6;
        }
        return -1L;
    }
    static long nthPrime(int nth) {
        int cnt = 0;
        int p = 2;
        while (true) {
            if (isPrime(p) == -1L) {
                cnt +=1;
//                System.out.println("p: " + p + ", cnt: " + cnt);
                if (cnt == nth)
                    return p;
            }
            p += 1;
            // todo observe overflow?
        }
    }
}
