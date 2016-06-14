package tmp;

import java.util.*;

/** A thread unsafe iterator over two lists to convert them into a map such that keys in first list at a certain
 * index map onto values in the second list <b> at the same index</b>.
 * Created by kmhaswade on 5/10/16.
 */
public class DualIterator<K, V> implements Iterable<Map.Entry<K, V>> {
    private final List<K> keys;
    private final List<V> values;
    private int anchor = 0;

    public DualIterator(List<K> keys, List<V> values) {
        // do all the validations here
        this.keys = keys;
        this.values = values;
    }
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<Map.Entry<K, V>>() {
            @Override
            public boolean hasNext() {
                return keys.size() > anchor;
            }

            @Override
            public Map.Entry<K, V> next() {
                Map.Entry<K, V> e = new AbstractMap.SimpleEntry<>(keys.get(anchor), values.get(anchor));
                anchor += 1;
                return e;
            }
        };
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("apple,orange,pear".split(","));
        List<String> things = Arrays.asList("123,456,789".split(","));
        Map<String, String> map = new LinkedHashMap<>(4);
        for (Map.Entry<String, String> e : new DualIterator<>(names, things)) {
            map.put(e.getKey(), e.getValue());
        }
        System.out.println(map);

    }
}
