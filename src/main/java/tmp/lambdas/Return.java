package tmp.lambdas;

import java.util.List;
import java.util.Random;

import static java.lang.Integer.compare;
import static java.util.Collections.sort;
import static java.util.stream.Collectors.toList;

/**
 * <p>
 * Is a return permissible in a lambda expression?
 * </p>
 * Created by kedar on 5/19/17.
 */
public class Return {
    public static void main(String[] args) {
        List<Integer> ints = new Random()
            .ints(10, -30, 30)
            .boxed()
            .collect(toList());
        sort(ints, (Integer a, Integer b) ->  {
            return compare(a, b); // return is allowed
        });
//        sort(ints, (a, b) -> Integer.compare(a, b));
//        sort(ints, Integer::compare);
        System.out.println(ints);
    }
}
