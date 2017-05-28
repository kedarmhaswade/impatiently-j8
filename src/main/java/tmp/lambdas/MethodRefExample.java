package tmp.lambdas;

import java.util.List;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

/**
 * <p>
 *     Method references are a way to make the code shorter.
 *     Like lambdas, it takes time to get used to the new syntax.
 * </p>
 * <p>
 *     Generally speaking, you should just take your (good) IDE's suggestions to
 *     replace lambdas by method references as you like.
 * </p>
 * Created by kedar on 5/16/17.
 */
public class MethodRefExample {
    public static void main(String[] args) {
        List<String> names = asList("larry", "moe", "curly");
        // lambda way
        Consumer<? super String > nameConsumer;
        nameConsumer = n -> System.out.println(n);
        names.forEach(nameConsumer);
        // method reference way
        nameConsumer = System.out::println;
        names.forEach(nameConsumer);
        // even shorter
        names.forEach(System.out::println);
    }
}
