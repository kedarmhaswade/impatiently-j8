package juc;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <p>Example of a parallel stream and then switching it to sequential.</p>
 */
public class ParallelStreamEx {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        numbers
            .parallelStream()
            .map(n -> n * 2)
            .peek(n -> System.out.println(Thread.currentThread().getName() + " processing: " + n))
            .sequential() // comment this line out! Remember that with streams, no processing is done until the terminal expression is reached, so it’s at that moment that the state of the stream is evaluated.
            .sorted()
            .collect(toList())
            .forEach(n -> System.out.print(n + " "));
    }
}
