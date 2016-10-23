package practice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import static java.util.Arrays.*;
import static java.util.stream.IntStream.*;

/**
 * <p>
 *     Some explorations in consistent hashing.
 * </p>
 * Created by kedar on 10/16/16.
 */
public class ConsistentHashingExp {

    // A simple hash function
    static final BiFunction<Long, Integer, Integer> H = (key, m) -> (int) ((7 * key) + 23) % m;
    // The jump consistent hash function is taken from https://arxiv.org/pdf/1406.2294.pdf
    static final BiFunction<Long, Integer, Integer> JC = (key, m) -> {
        int b = -1, j = 0;
        while (j < m) {
            b = j;
            key = (key * 2862933555777941757L) + 1;
            j = (int) ((b + 1) * ((double)(1L << 31)) / ((double)((key >> 33) + 1)));
        }
        return b;
//        return b < 0 ? -(b % m): b % m;
    };
//    static int h(int x, int m) {
//        int hash =  ((7 * x) + 23) % m;
////        System.out.println(hash);
//        return hash;
//    }
    static int itemsMoving(int fromInc, int toExc, int n1, int n2,
                           // accept the bifunction, parameterize the behavior
                           BiFunction<Long, Integer, Integer> hf) {
        int nMovers = 0;
        for (long i = fromInc; i < toExc; i++) {
            if (hf.apply(i, n1).intValue() != hf.apply(i, n2).intValue()) // h(i, n1) != h(i, n2)
                nMovers += 1;
        }
        return nMovers;
    }

    public static void main(String[] args) {
        final int LIM = 10_000;
        int n1 = 10, n2 = 12;
//        System.out.println(itemsMoving(0, LIM, n1, n2));
        System.out.println("Number of buckets changed from " + n1 + " to " + n2);
        int moved = itemsMoving(0, LIM, n1, n2, H);
        System.out.println("Number of items moving when using simple hash function: " + moved + " %age: " + (moved * 100.0)/LIM);
        moved = itemsMoving(0, LIM, n1, n2, JC);
        System.out.println("Number of items moving when using jump consistent hash function: " + moved + " %age: " + (moved * 100.0)/LIM);
//        System.out.println("distributions:" );
//        System.out.println("simple hash function: ");
//        printDistributions(LIM, n1, H);
//        System.out.println("jump consistent hash function: ");
//        printDistributions(LIM, n1, JC);
    }

//    private static void printDistributions(int lim, int m) {
//        int[] a = new int[m];
//        range(0, lim).forEach(i -> a[h(i, m)] += 1);
//        stream(a).forEach(System.out::println);
//    }
        private static void printDistributions(int lim, int m,
                                               BiFunction<Long, Integer, Integer> hf) {
        int[] a = new int[m];
        range(0, lim).forEach(i -> a[hf.apply((long)i, m)] += 1);
        stream(a).forEach(System.out::println);
    }

}
