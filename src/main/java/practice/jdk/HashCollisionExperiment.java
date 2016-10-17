package practice.jdk;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * <p>
 *     Examines hash printCollisionReport with the JDK's HashMap implementation. <a href="http://preshing.com/20110504/hash-collision-probabilities/">Preshing </a>
 *     suggests that with 32-bit hash values (i.e. what {@linkplain Object#hashCode()} returns), we have
 *     a 50% probability of having a collision with only 77,163 elements. This is an attempt to examine what I find.
 * </p>
 * Created by kedar on 1/10/16.
 */
public class HashCollisionExperiment {
    public static void main(String[] args) {
        HashMap<Object, Integer> map = new HashMap<>(1<<17);
        int limit = 77_163;
        Random r = new Random();
        IntStream.rangeClosed(0, limit).forEach(x -> map.put(randomHexString(r, 8), r.nextInt()));
        map.printCollisionReport();
    }
    static String randomHexString(Random r, int len) {
        char[] cs = new char[len];
        String x = "0123456789ABCDE";
        for (int i = 0; i < len; i++) {
            cs[i] = (char) r.nextInt(x.length());
        }
        return new String(cs);
    }
}
