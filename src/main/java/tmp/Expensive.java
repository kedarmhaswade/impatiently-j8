package tmp;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kmhaswade on 4/21/16.
 */
public class Expensive {
    static final Random r = new Random();
    public static void main(String[] args) {
        Map.Entry<Integer, String> e =
        Stream.of("larry", "moe", "curly", "more", "stooge")
                .collect(Collectors.toMap(Expensive::coolness,
                                          Function.identity(),
                                          (a, b) -> a,
                        () -> new TreeMap<>((x, y) -> Integer.compare(y, x))
                        ))
                .firstEntry();
        System.out.println("most expensive: " + e.getKey() + ", coolness: " + e.getValue());
    }

    public static int coolness(String s) {
        // simulation of a call that takes time.
        int x = r.nextInt(100);
        System.out.println(x);
        return x;
    }
    private class Member {

    }
    private static class Club {
        //thread unsafe
        private Member[] members;
        private int nCurrentMembers = 0;

        Club(int initialCapacity) {
            //validate initialCapacity
            members = new Member[initialCapacity];
        }

        public boolean join(Member m) {
            //validate m
            if (nCurrentMembers == members.length) {
                members = Arrays.copyOf(members, members.length + 100);
            }
            members[nCurrentMembers++] = m;
            return true;
        }
        public boolean leave(Member m) {
            // homework :-)
            return true;
        }
    }
}
