package practice.ej;

import java.util.HashSet;
import java.util.Set;

/**
 * Puzzling {@link Object#equals(Object)}.
 * <p>
 *     Demonstrates a rather puzzling (only if you don't already know) fact about the equals method.
 * </p>
 */
public class OrderlyNameTester {
    public static void main(String[] args) {
        Set<OrderlyName> set = new HashSet<>();
        OrderlyName larry = new OrderlyName("Larry", "Fine");
        OrderlyName moe = new OrderlyName("Moe", "Howard");
        OrderlyName curly = new OrderlyName("Curly", "Howard");
        boolean added = set.add(larry);
        assert added;
        added = set.add(moe);
        assert added;
        added = set.add(curly);
        assert added;
        System.out.println("Three Stooges:");
        System.out.println(set);
        System.out.println("Is Larry Fine a stooge?");
        System.out.println(set.contains(new OrderlyName("Larry", "Fine")));
    }
}
