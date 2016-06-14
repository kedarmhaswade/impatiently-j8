package tmp;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**<p>
 * The {@linkplain java.util.stream.Stream#reduce(Object, BiFunction, BinaryOperator)}
 * method allows reducing the stream to a result based on some _property_
 * U of the type of objects that the stream is made of. An example would be to get the
 * total _length of strings_ in a stream of strings.
 * The combiner helps so that reduction is not necessarily sequential.
 * Can the combiner be null?
 * </p>
 * Created by kmhaswade on 3/18/16.
 */
public class ReduceProperty {
    public static void main(String[] args) {
        String[] names = {"Larry", "Moe", "Curly"};
        int t = Stream.of(names).reduce(0, (total, word) -> total += word.length(), (total1, total2) -> total1 += total2);
        System.out.println("total: " + t);
        BiFunction<String, Object, String> accumulator = (a,b) -> a + b;
        System.out.println(Stream.of("foo", "bar", "baz").reduce("text: ", accumulator, (a, b) -> a + b));
        System.out.println(Stream.of(1, -2, 3).reduce("long num: ", accumulator, (a, b) -> a + b));
    }
}
