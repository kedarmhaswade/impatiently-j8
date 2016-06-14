package tmp;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import static java.util.stream.Collectors.joining;

/** Joining example
 * Created by kmhaswade on 3/20/16.
 */
public class ConcatEx {
    public static void main(String[] args) {
        System.out.println(Stream.of("foo", "bar", "baz").collect(joining(", ")));
//        System.out.println(Stream.of(1, 2, 3).collect(joining())); // not possible, first convert to strings
        System.out.println(Stream.of(1, 2, 3).map(Object::toString).collect(joining(", ")));
    }
}
