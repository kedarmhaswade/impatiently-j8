package tmp;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * <p>
 *     Is Lambda Expression based code not equivalent to the traditional for loop?
 * </p>
 * Created by kedar on 2/24/17.
 */
public class LambdaEquivalent {

    private static final AtomicInteger count1 = new AtomicInteger();
    private static final AtomicInteger count2 = new AtomicInteger();

    private static volatile int v1 = 0;
    private static volatile int v2 = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            count1.getAndIncrement(); // ignore return value
        }
        System.out.println(count1.get());
        IntStream.rangeClosed(1, 100).forEach(i -> count2.getAndIncrement());
        System.out.println(count2.get());

        for (int i = 0; i < 100; i++) {
            v1++;
        }
        System.out.println(v1);
        IntStream.rangeClosed(1, 100).forEach(i -> v2++);
        System.out.println(v2);
    }
}
