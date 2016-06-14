package tmp;

import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

/**
 * How to use {@linkplain Collectors#collectingAndThen(Collector, Function)}
 * Created by kmhaswade on 4/13/16.
 */
public class CollectingAndThenEx {
    /*
    Collectors.collectingAndThen:

     */
    public static void main(String[] args) {
        Stream.of("harry", "moe", "curly") // should be unique
//                .collect(toMap(Function.identity(), String::length))
                .collect(collectingAndThen(
                        toMap(Function.identity(), String::length),
                        (from -> Collections.unmodifiableMap(from))
                ))
                .forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
