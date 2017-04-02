package tmp;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by kedar on 3/31/17.
 */
public class FiveNumbers {
    public static void main(String[] args) {
        for (int x = 0; x <= 9; x++) {
            for (int y = 0; y <= 9; y++) {
                for (int z = 0; z <= 9; z++) {
                    for (int a = 0; a <= 9; a++) {
                        for (int b = 0; b <= 9; b++) {
                            if (subsetSumStream(x, y, z, a, b)
                                .peek(System.out::println)
                                .noneMatch(n -> n % 5 == 0)
                                ) {
                                System.out.println(x + ", " + y + ", " + z + ", " + a + ", " + b);
                            }
                        }
                    }
                }
            }
        }
    }
    private static Stream<Integer> subsetSumStream(int x, int y, int z, int a, int b) {
        return Stream.of(
            IntStream.of(x), IntStream.of(y), IntStream.of(z), IntStream.of(a), IntStream.of(b),
            IntStream.of(x, y), IntStream.of(x, z), IntStream.of(x, a), IntStream.of(x, b),
            IntStream.of(y, z), IntStream.of(y, a), IntStream.of(y, b),
            IntStream.of(z, a), IntStream.of(z, b),
            IntStream.of(a, b),
            IntStream.of(z, a, b), IntStream.of(y, a, b), IntStream.of(y, z, b), IntStream.of(y, z, a),
            IntStream.of(x, a, b), IntStream.of(x, z, b), IntStream.of(x, z, a),
            IntStream.of(x, a, b), IntStream.of(x, y, a),
            IntStream.of(x, y, z),
            IntStream.of(x, y, z, a), IntStream.of(x, y, z, b),
            IntStream.of(y, z, a, b), IntStream.of(x, z, a, b),
            IntStream.of(x, y, a, b),
            IntStream.of(x, y, z, a, b)
        ).map(ints -> ints.reduce(Integer::sum).getAsInt());
    }
}
