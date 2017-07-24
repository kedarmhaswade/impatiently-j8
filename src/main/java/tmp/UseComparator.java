package tmp;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by kedar on 6/12/17.
 */
public class UseComparator {
    public static void main(String[] args) {
//        String s1 = args[0];
//        String s2 = args[1];
//        Comparator<String> cmp = (s, t) -> Integer.compare(s.length(), t.length());
//        int c = cmp.compare(s1, s2);
//        System.out.println(c < 0 ? "smaller, bigger" : "bigger, smaller");

        Set<String> unordered = Sets.newHashSet("a", "aa", "bb", "b");
        TreeSet<? extends Comparable> ordered = new TreeSet<>(unordered);
        ordered.stream().collect(Collectors.toList()).forEach(System.out::println);
    }
}
