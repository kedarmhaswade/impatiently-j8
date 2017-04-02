package tmp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

/**
 * <p>
 *     Exactly what is {@link java.util.Map#merge(Object, Object, BiFunction) Map#merge} for?
 * </p>
 * Created by kedar on 3/4/17.
 */
public class MapMergeEx {
    public static void main(String[] args) {
        Map<String, Long> map = new HashMap<>();
        String key1 = "foo";
        String key2 = "bar";
        map.merge(key1, 1L, Long::sum); // return value ignored
        map.merge(key2, 2L, Long::sum); // return value ignored
        for (String key : map.keySet()) {
            System.out.println("key: " + key + " value: " +  map.get(key));
        }
        map.put(key1, 2L);
        map.merge(key1, 20L, Long::sum);
        System.out.println("after: " + map.get(key1));
    }
}
