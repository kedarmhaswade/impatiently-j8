package tmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kmhaswade on 8/19/16.
 */
public class X {
    public static void main(String[] args) {
        int cap = 10;
        List<Integer> xs = new ArrayList<>(cap);
        for (int i = 0; i < cap; i++)
            xs.add(0);
        System.out.println(xs);
        xs = Arrays.asList(1, 2, 3);
        xs.set(0, 100);
        System.out.println(xs);
        @SuppressWarnings("unchecked")
        List<String>[] lol = new List[4];
        lol[0] = Arrays.asList("a", "b", "c");
        List<String> l = Arrays.asList("a", "b");
    }
}
