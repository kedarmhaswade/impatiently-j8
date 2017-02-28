package tmp;

import com.google.common.collect.ImmutableMap;

/**
 * Created by kedar on 2/1/17.
 */
public class ImmutableMapTest {
    public static void main(String[] args) {
        ImmutableMap<String, String> fixed = ImmutableMap.of("a", "b", "c", "d");
        System.out.println(fixed);
     }
}
