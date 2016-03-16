package tmp;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by kmhaswade on 3/2/16.
 */
public class LargestNumber {
    public static void main(String[] args) {
        int[] a = {9, 90};
        Integer[] numbers = new Integer[]{9, 90, 9};
        String s = Stream.of(numbers)
                .filter(n -> n >= 0)
                .map(Object::toString)
                .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2))
                .collect(Collectors.joining());
        System.out.println(s);

    }

}
