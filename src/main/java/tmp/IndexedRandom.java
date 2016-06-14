package tmp;

import java.util.Random;

/** Someone asked to compare the ith random number generated from a PRNG. Is Java 8 streams implementation faster?
 * Created by kmhaswade on 4/28/16.
 */
public class IndexedRandom {
    private static int naiveNth(int n) {
        Random r = new Random(1342); // you can use some seed
        if (n <= 0)
            throw new IllegalArgumentException("n not >= 1: " + n);
        int nth = 0;
        for (int i = 0; i < n; i ++) {
            nth = r.nextInt();
//            System.out.println(nth);
        }
        return nth;
    }
    private static int streamNth(int n) {
        Random r = new Random(1342); // you should use the same seed
        return r.ints()
//                .peek(i -> System.out.println(i))
                .skip(n-1)
                .findFirst()
                .getAsInt();
    }

    public static void main(String[] args) {
        int n = 1_000_000;
        long t1 = System.currentTimeMillis();
        int n1 = naiveNth(n);
        System.out.println("Naive approach took: " + (System.currentTimeMillis() - t1) + " ms");
        t1 = System.currentTimeMillis();
        int n2 = streamNth(n);
        System.out.println("Stream approach took: " + (System.currentTimeMillis() - t1) + " ms");
        assert n1 == n2 : "n1: " + n1 + ", n2: " + n2;
    }
}
