package tmp;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by kmhaswade on 2/23/16.
 */
public class StreamOf {
    public static void main(String[] args) {
        //does not do what you want!
        int[] ints = {1, 2, 3};
        System.out.println(Stream.of(ints).count());
        //does what you want!
        Arrays.stream(ints).forEach(System.out::println);
    }
}
