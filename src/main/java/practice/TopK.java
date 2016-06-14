package practice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * <p>A rudimentary Top-K implementation for Map.Entry&lt;Object, Integer>.
 * </p>
 * Created by kmhaswade on 4/27/16.
 */
public class TopK {


    public static void main(String[] args) {
        // k-element minHeap
        int k = Integer.parseInt(args[0]);
        PriorityQueue<Entry<String, Long>> minHeap =
                new PriorityQueue<>(k, (a, b) -> Long.compare(a.getValue(), b.getValue()));
        Set<Entry<String, Long>> words = words(args[1]);
        Iterator<Entry<String, Long>> iter = words.iterator();
        for (int i = 0; i < k; i++) {
            if (iter.hasNext()) {
                Entry<String, Long> word = iter.next();
                minHeap.add(word);
                iter.remove();  // note: optional operation
            } else {   // we have fewer than k entries, unlikely, but possible
                printQ(minHeap);
                return; // we are done
            }
        }
        // since there are more words we remove and add, if we are sure
        for (Entry<String, Long> e : words) {
            Entry<String, Long> head = minHeap.peek();
            if (Long.compare(head.getValue(), e.getValue()) < 0) { // head is smaller than e
                minHeap.remove();
                minHeap.add(e);
            }
        }
        printQ(minHeap);
    }

    private static void printQ(PriorityQueue<Entry<String, Long>> minHeap) {
        int i = 0;
        while (!minHeap.isEmpty()) {
            Entry<String, Long> e = minHeap.remove();
            System.out.println(e.getKey() + ": " + e.getValue());
            i += 1;
        }
        System.out.println("These are the top " + i + "entries");
    }

    private static Set<Entry<String, Long>> words(String path) {
        try {
            return Files.lines(Paths.get(path))
                    .flatMap(s-> Arrays.stream(s.split("\\p{javaWhitespace}")))
                    .filter(s -> s.length() >= 3)
                    .map(s -> s.replaceAll("\\p{Punct}", ""))
                    .map(String::toLowerCase)
                    .collect(groupingBy(Function.identity(), counting()))
                    .entrySet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
