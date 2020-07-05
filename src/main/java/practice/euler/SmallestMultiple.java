package practice.euler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * *** is the smallest number that can be divided by each of the numbers from 1 to 10
 * without any remainder.
 * <p>
 * What is the smallest positive number that is evenly divisible by all of the numbers
 * from 1 to 20?
 */
public class SmallestMultiple {
    public static void main(String[] args) {
        int[] a = new int[19];
        for (int i = 0; i < 19; i++) {
            a[i] = i + 2;
        }
        System.out.println(lcm(a));
    }

    /**
     * Finds the LCM of all the numbers in given array. The array is assumed sorted.
     *
     * @param a
     * @return
     */
    static long lcm(int[] a) {
        Map<Long, Integer> pff = new HashMap<>(); // frequency of prime factors needed
        int len = a.length;
        char[] sieve = LargestPrimeFactor.sieve((int) Math.ceil(Math.sqrt(a[len - 1])) + 1);
        for (int i = len - 1; i >= 0; i--) {
            int n = a[i];
            Map<Long, Integer> primeFactors = new HashMap<>();
            LargestPrimeFactor.largestIterative(n, sieve, 2, primeFactors);
//            System.out.println("pfs of: " + n + " = " + primeFactors);
            primeFactors.forEach((k, v) -> pff.put(k, Math.max(primeFactors.get(k), pff.get(k) == null ? 0 : pff.get(k))));
        }
        assert !pff.isEmpty();
        System.out.println(pff.entrySet());
        long p = 1;
        for (Map.Entry<Long, Integer>e : pff.entrySet()) {
            p *= Math.pow(e.getKey(), e.getValue());
        }
        return p;
    }

}
