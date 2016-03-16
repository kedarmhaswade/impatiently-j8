package tmp;

import java.math.BigInteger;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Created by kmhaswade on 2/24/16.
 */
public class InfiniteStreams {
    static Stream<Integer> evens(int start) {
        return Stream.iterate(start % 2 == 0 ? start : start + 1, i -> i + 2);
    }

    public static void main(String[] args) {
        evens(1).limit(10).forEach(System.out::println);
        Stream<BigInteger> integers
                = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
        integers.limit(10).forEach(System.out::println);
        Optional<Integer> opt = evens(1).limit(5).reduce(Integer::sum);
        opt.ifPresent(System.out::println);
    }
}
