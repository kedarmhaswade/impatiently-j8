package tmp;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;

/**
 * Created by kedar on 5/4/17.
 */
public class LetsPlayBoxing {

    public static void main(String[] args) {
        List<Integer> a = asList(-100, -200, 24, 42, 0);
        List<Integer> b = asList(-100, -200, 24, 42, 0);
        sort(a);
        sort(b);
        System.out.println(a.get(0) == b.get(0));
        System.out.println(a.get(1) == b.get(1));
    }
}
