package org.kedar.java.reallyimp.ch1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * <p>
 *     Form a subinterface Collection2 from Collection and add a default method
 *     void forEachIf(Consumer<T> action, Predicate<T> filter) that applies
 *     action to each element for which filter returns true. How could you use it?
 * </p>
 * Created by kmhaswade on 2/20/16.
 */
public class ForEachIfP9 {
    interface Collection2<E> extends Collection<E> {
        default void forEachIf(Consumer<E> action, Predicate<E> filter) {
            forEach(e -> {
                if (filter.test(e))
                    action.accept(e);
            });
        }
    }
    static class ArrayList2<E> extends ArrayList<E> implements Collection2<E> {

    }
    public static void main(String[] args) {
        Collection2<Integer> list = new ArrayList2<>();
        for (int i = 0; i < 10; i++)
            list.add(i);
        list.forEachIf(System.out::println, e -> e % 2 == 0);
    }
}
