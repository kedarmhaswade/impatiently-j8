package tmp;

import java.math.BigInteger;
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
        evens(Integer.MAX_VALUE - 100).limit(100).forEach(System.out::println);
        Stream<BigInteger> integers
                = Stream.iterate(BigInteger.ZERO, new UnaryOperator<BigInteger>() {
            @Override
            public BigInteger apply(BigInteger n) {
                return n.add(BigInteger.ONE);
            }
        });

    }
}
