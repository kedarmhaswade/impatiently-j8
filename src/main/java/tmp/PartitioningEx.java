package tmp;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/** Example partitioning of a stream.
 * Created by kmhaswade on 3/25/16.
 */
public class PartitioningEx {
    public static void main(String[] args) {
        //partition the given ints into odd and even
        Random r = new Random();
        Map<Boolean, List<Integer>> boxed = r.ints(0, 101)
                .limit(10)
                .boxed()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println(boxed.get(true));
        System.out.println(boxed.get(false));
        // r.ints returns an IntStream (stream of primitive ints which requires boxing/unboxing
        // can we improve?
    }
}
