package tmp;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by kmhaswade on 4/14/16.
 */
public class PrimitiveStreamEx {
    public static void main(String[] args) {
        int[] ints = new int[] {21, 5, 25, -2, 4, 6};
        Arrays.stream(ints, 3, 5).forEach(System.out::println); // =>
        IntStream.of(-3, 5, 24).forEach(System.out::println);
        Stream.of(-3, 5, 24).forEach(i -> System.out.println(i.getClass()));
    }
}
