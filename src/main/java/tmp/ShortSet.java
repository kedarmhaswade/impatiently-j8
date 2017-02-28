package tmp;

import java.util.HashSet;
import java.util.Set;

public class ShortSet {
    public static void main (String[] args) {
        Set<Short> s = new HashSet<>();
        for (short i = 0; i < 100; i++) {
            boolean added = s.add(i);
            assert added;
            boolean removed = s.remove(i - 1);
            assert removed;
        }
        System.out.println(s.size());
    }
}