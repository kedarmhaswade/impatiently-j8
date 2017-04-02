package juc;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *     Example to understand the use of the set returned by {@link ConcurrentHashMap#newKeySet()}
 *     method. The returned objects implements {@linkplain java.util.Set}.
 * </p>
 * Created by kedar on 3/10/17.
 */
public class KeySetViewEx {
    public static void main(String[] args) {
        Set<String> cs = ConcurrentHashMap.newKeySet(100);
        add(cs, "foo");
        add(cs, "bar");
        add(cs, "baz");
        System.out.println(cs.contains("bar"));
        System.out.println(cs.contains("foo"));
        cs.remove("bar");
        System.out.println(cs.contains("bar"));
    }
    static void add(Set<String> set, String e) {
        boolean added = set.add(e);
        assert added;
    }
}
