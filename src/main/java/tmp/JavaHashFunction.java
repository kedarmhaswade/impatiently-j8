package tmp;

import java.util.Random;

/**
 * <p>
 *     Tests the hash function of Java's {@linkplain java.util.HashMap} for collisions.
 * </p>
 * Created by kedar on 3/30/17.
 */
public class JavaHashFunction {

    /**
     * This is the copy of {@link java.util.HashMap#hash(Object)}.
     * @param key
     * @return
     */
    static final int hash(Long key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    public static void main(String[] args) {
//        all();
        int non = 50_000_000;
        random(non);
    }

    private static void random(int non) { // non is number of numbers
        Random r = new Random();
        long key = r.nextLong();
        int keyHash = hash(key);
        long start = 23233; //r.nextLong();
        long end = Math.addExact(start, non);
        System.out.println("key: " + key + ", key hash: " + keyHash);
        System.out.println("In the range: " + start + ", " + end + ", following numbers have the same hash (each is a conflict) ...");
        r.longs(start, end)
            .filter(candidate -> Integer.compare(keyHash, hash(candidate)) != 0)
            .forEach(x -> System.out.println(hash(x) + ", " + keyHash));
    }

    static void all() {
        int nc = 0;
        long lo = Long.MIN_VALUE;
        long hi = Long.MAX_VALUE;
        for (long out = lo; out <= hi; out++) {
            int ohash = hash(out);
            for (long in = lo; in <= hi; in++) {
                if (out != in) {
                    if (ohash == hash(in)) {
                        nc += 1;
                        System.out.println("conflict for: in: " + in + ", out: " + out);
                        System.out.println("conflicts so far:" + nc);
                    }
                }
            }
        }
        System.out.println("number of collisions: " + nc);
    }
}
