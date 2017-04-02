package tmp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>
 *     When might {@link java.util.Map#put(Object, Object) Map#put} throw a {@link ClassCastException}?
 * </p>
 * Created by kedar on 3/4/17.
 */
public class MapClassCastException {
    public static void main(String[] args) {
        Map<Integer, String> m = new TreeMap<>();
        unsafePut(m);
        System.out.println(m.get(1));
    }

    private static void unsafePut(Map m) {
        m.put("blow", "up");
    }
}
