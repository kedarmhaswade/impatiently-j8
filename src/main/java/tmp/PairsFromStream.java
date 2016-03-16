package tmp;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An attempt for for http://stackoverflow.com/questions/20470010/collect-successive-pairs-from-a-stream
 * Created by kmhaswade on 3/2/16.
 */
public class PairsFromStream {
    static class Pair<U, V> {
        private final U u;
        private final V v;
        Pair (U u, V v) {
            this.u = u;
            this.v = v;
        }
        @Override
        public String toString() {
            return "(" + u + ", " + v + ")";
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        Iterator<Integer> first = list.iterator();
        first.next();
        if (first.hasNext())
            list.stream().skip(1).map(v -> new Pair(first.next(), v)).forEach(System.out::println);
    }
}
