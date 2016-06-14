package tmp;

import java.util.*;

/**
 * Created by kmhaswade on 5/11/16.
 */
public class NotConcurrentModification {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>(2);
        map.put("Larry", 1);
        map.put("Moe", 2);
        System.out.println(map);
        Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Integer> e = iter.next();
            map.put(e.getKey(), e.getValue() + 100);
        }
        System.out.println(map);

        List<String> names = Arrays.asList("Larry", "Moe", "Curly");
        int i = 0;
        Iterator<String> strIter = names.iterator();
        while (strIter.hasNext()) {
            names.set(i, strIter.next() + " " + i);
            i += 1;
        }
        System.out.println(names);
    }
}
