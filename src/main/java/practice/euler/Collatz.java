package practice.euler;

import java.util.Map;

/**
 * <p>
 *     See: <a href="https://projecteuler.net/problem=14">Problem 14 on Project Euler</a>.
 * </p>
 *
 <p>
 The following iterative sequence is defined for the set of positive integers:

 n → n/2 (n is even)
 n → 3n + 1 (n is odd)
 </p>

 <p>Using the rule above and starting with 13, we generate the following sequence:
</p>
 <p>
 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 </p>
 <p>It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

 Which starting number, under one million, produces the longest chain?
 </p>
 NOTE: Once the chain starts the terms are allowed to go above one million.
 <p>
 Created by kedar on 11/1/16.
 </p>
 */
public class Collatz {

    public static int findLongestChainIter(int limit) {
        int max = Integer.MIN_VALUE;
        int maxi = Integer.MIN_VALUE;
        for (int i = 1; i <= limit; i++) {
            int tmp = getChainLength(i);
            if (tmp > max) {
                max = tmp;
                maxi = i;
            }
        }
        System.out.println("max: " + max + ", for i: " + maxi);
        return max;
    }
    public static int getChainLength(long n) {
        int c = 1;
        while (n != 1) {
            n = n % 2 == 0 ? n >>> 1 : Math.addExact(Math.multiplyExact(3, n), 1);
            c += 1;
        }
        return c;
    }
    public static int findLongestChain(int limit, Map<Integer, Integer> cache) {
        // limit is inclusive
        cache.put(1, 1);
        populatePowersOf2(limit, cache);
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= limit; i++) {
            max = Math.max(max, memoize(i, cache));
        }
        return max;
    }

    private static int memoize(int i, Map<Integer, Integer> cache) {
        // returns the length of Collatz chain for integer i
        // both using and updating the chain.
        // Note: overflow could occur
//        System.out.println("i: " +i);
        Integer val = cache.get(i);
        if (val != null)
            return val;
        // we assume that Collatz conjecture holds!
        int count = 0;
        if (i % 2 == 0)
            count = 1 + memoize(i / 2, cache);
        else
            count = 1 + memoize((3 * i) + 1, cache);
        cache.put(i, count);
        return count;
    }

    private static void populatePowersOf2(int limit, Map<Integer, Integer> cache) {
        // length of Collatz chain for 2^p = p + 1 (for all p >= 1)
        int c = 1;
        for (int i = 2; i > 0 && i <= limit; ) {
            cache.put(i, c + 1);
            i *= 2;
            c += 1;
        }
    }
}
