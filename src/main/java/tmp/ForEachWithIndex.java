package tmp;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

/** Many Stack Overflow answers may have this, but can I code it on my own?
 * Created by kmhaswade on 3/11/16.
 */
public class ForEachWithIndex {
    static <T> void forei(T[] xs, BiConsumer<Integer, T> c, Supplier<Integer>... init) {
        int i = init.length == 0 ? 0 : init[0].get();
        for (T x : xs)
            c.accept(i++, x);
    }

    public static void main(String[] args) {
        forei(new String[]{"Larry", "Moe", "Curly"}, (i, s) -> System.out.println(i + ": " + s));
        forei(new Double[]{Math.PI, Math.E}, (i, s) -> System.out.println(i + ": " + s), () -> 100);
    }
}
