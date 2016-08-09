package tmp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kmhaswade on 8/2/16.
 */
public class HashMapTest {
    static final class BadKey {
        final int n;
        BadKey(int n) {
            this.n = n;
        }
        @Override
        public int hashCode() {
            return 4;
        }
        @Override
        public boolean equals(Object o) {
            return o instanceof BadKey && ((BadKey)o).n == this.n;
        }
    }
    public static void main(String[] args) {
        Map<BadKey, String> map = new HashMap<>();
        BadKey k1 = new BadKey(0);
        BadKey k1Copy = new BadKey(0);
        BadKey k2 = new BadKey(1);
        System.out.println(System.identityHashCode(k1));
        System.out.println(System.identityHashCode(k1Copy));
        map.put(k1, "k1");
        map.put(k2, "k2");
        map.put(k1Copy, "k1Copy");
        System.out.println(map.get(k1Copy));
        System.out.println(map.containsKey(k1Copy));
        System.out.println(map.size());

    }
}
