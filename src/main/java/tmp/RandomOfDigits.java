package tmp;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by kmhaswade on 5/13/16.
 */
public class RandomOfDigits {
    static final Random r = new Random();
    public static void main(String[] args) {
        System.out.println("ThreadLocalRandom:");
        compare(1);
        System.out.println("Homegrown:");
        compare(2);
//        IntSummaryStatistics summary  = histo.values().stream().collect(Collectors.summarizingInt(i -> i.intValue()));
//        System.out.println(summary);
//        System.out.println("range: " + (summary.getMax() - summary.getMin()));
    }

    public static void compare(int cm) {
        int nd = 4;
        // now after calling the routine, we should get the histogram for 1000 to 9999 that is more or less uniform
        Map<Integer, Integer> histo = new HashMap<>(1<<14);
        int times = 10_000_000;
        int loIn = (int) Math.pow(10, nd-1);
        int hiEx = loIn * 10;
        for (int i = 0; i < times; i++) {
            int x = (cm == 1 ? useThreadLocalRandom(loIn, hiEx) : uarInRange(loIn, hiEx));
            if (!histo.containsKey(x))
                histo.put(x, 1);
            histo.put(x, histo.get(x) + 1);
        }
        System.out.println(histo.entrySet().stream().sorted((e1, e2) -> Integer.compare(e1.getKey(), e2.getKey()))
//                .map(e -> new AbstractMap.SimpleEntry<>(e.getKey(), e.getValue() * 100.0 / times))
                .collect(Collectors.toList()));
        IntSummaryStatistics summary  = histo.values().stream().collect(Collectors.summarizingInt(i -> i.intValue()));
        System.out.println("each number should appear this many times: " + (summary.getSum() * 1.0 / (hiEx -
                loIn)));
        System.out.println("range: " + (summary.getMax() - summary.getMin()));

    }
    public static int uarInRange(int loIn, int hiEx) {
        int x;
        do {
            x = r.nextInt(hiEx);
//            System.out.println("attempt: " + x);
        } while (x < loIn);
        return x;
    }
    public static int useThreadLocalRandom(int loIn, int hiEx) {
        return ThreadLocalRandom.current().nextInt(loIn, hiEx);
    }
}
