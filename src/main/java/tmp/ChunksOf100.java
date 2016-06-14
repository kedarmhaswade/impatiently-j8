package tmp;

import java.util.Random;
import java.util.stream.LongStream;

/**
 * <a href="http://stackoverflow.com/questions/36538557/how-do-i-convert-a-long-of-thousands-of-elements-to
 * -substreams-of-100-element"> so problem </a>.
 *
 * Created by kmhaswade on 4/10/16.
 */
public class ChunksOf100 {
    private static final Random r = new Random();
    public static void main(String[] args) {
        final long limit = 30_000;
        LongStream.rangeClosed(1, limit)
                .filter(l -> myPredicate(l));
//                .collect()
    }

    private static boolean myPredicate(long l) {
        return r.nextBoolean();
    }
}
