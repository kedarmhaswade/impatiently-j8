package tmp;

import java.util.stream.Stream;

/** <p>
 * Tries to answer what the total length of all strings in a stream is.
 * </p>
 * Created by kmhaswade on 3/14/16.
 */
public class TotalLength {
    public static void main(String[] args) {
        // suppose you want the total length of all strings in a stream
        // note that "lengths" of stream elements is what you want to reduce
        Stream<String> strings = Stream.of("Larry", "Moe", "Curly");
        // then you can't use this:
        // strings.reduce(0, (x, y) -> x.length() + y.length());
        // because the type of result of applying op is integer and there is no .length method in it
        // You need to use:
        Integer superTotal = strings.reduce(0,
                (total, word) -> total + word.length(),
                (total1, total2) -> total1 + total2);
        System.out.println(superTotal);
    }
}