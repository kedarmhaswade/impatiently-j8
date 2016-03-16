package tmp;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by kmhaswade on 3/15/16.
 */
public class SetComparison {
    /**
     * Jane asked how we can compare difference between certain property of elements in two different sets
     */
    static class Kit {
        long key;
        Kit(long key) {
            this.key = key;
        }
    }

    public static void main(String[] args) {
        Kit k11 = new Kit(13);
        Kit k12 = new Kit(22);
        Kit k13 = new Kit(33);
        Set<Kit> first = new HashSet<>();
        first.add(k11);
        first.add(k12);
        first.add(k13);
        Kit k21 = new Kit(113);
        Kit k22 = new Kit(122);
        Kit k33 = new Kit(33);
        Set<Kit> second = new HashSet<>();
        second.add(k21);
        second.add(k22);
        second.add(k33);
        System.out.println(minByKeyGap(first, second));
    }
    static long minByKeyGap(Set<Kit> first, Set<Kit> second) {
        if (first.isEmpty() || second.isEmpty())
            throw new IllegalArgumentException("either set is empty, minimum key gap is undefined");
        List<Long> gaps = new ArrayList<>(); // this is totally unnecessary
        // this problem cracks open my comfort with functional programming ...
        first.forEach((k1) -> second.forEach((k2) -> gaps.add(Math.abs(k2.key - k1.key))));
        System.out.println(gaps);
        return gaps.stream().min((s1, s2) -> Long.compare(s1, s2)).get();
    }

}
