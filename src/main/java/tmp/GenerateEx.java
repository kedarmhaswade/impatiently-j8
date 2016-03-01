package tmp;

import java.util.stream.Stream;

/**
 * Created by kmhaswade on 2/24/16.
 */
public class GenerateEx {
    public static void main(String[] args) {
        Stream.generate(() -> "y")
                .limit(10)
                .forEach(System.out::println);
    }
}
