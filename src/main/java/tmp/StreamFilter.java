package tmp;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Created by kmhaswade on 8/15/16.
 */
public class StreamFilter {
    public static void main(String[] args) {
        final AtomicInteger count = new AtomicInteger();
        IntStream.of(1, 2, 3).filter(i -> i % 2 == 0).forEach(
                i -> {
                    // do something with the i, e.g. write to a file, maybe
                    count.incrementAndGet(); // use return value
                });
        System.out.println(count.intValue());
    }
}
