package tmp;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

public class BiPredicateTest {
    public static void main(String[] args) {
        BiPredicate<List<Integer>, Integer> containsInt = List::contains;
        List<Integer> ints = java.util.Arrays.asList(1,20,20);
        ints.add(1);
        ints.add(20);
        ints.add(20);
        System.out.println(containsInt.test(ints, 20));

        BiConsumer<List<Integer>, Integer> listInt = BiPredicateTest::consumeMe;
        listInt.accept(ints, 15);

    }

    public static void consumeMe(List<Integer> ints, int num) {
        ints.removeIf(i -> i>num);
        ints.forEach(System.out::println);
    }
}