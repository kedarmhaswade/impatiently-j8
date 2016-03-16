package tmp;

import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

/** Count the frequency of the top n most common words in the "War and Peace".
 * Created by kmhaswade on 3/10/16.
 */
public class WarAndPeace {
    public static void main(String[] args) throws Exception {
            Files.lines(Paths.get("/tmp", "/war-and-peace.txt"))
            .map(line -> line.replaceAll("\\p{Punct}", ""))
            .flatMap(line -> Arrays.stream(line.split("\\s+")))
            .filter(word -> word.matches("\\w+"))
            .map(s -> s.toLowerCase())
            .filter(s -> s.length() >= 2)
            .collect(Collectors.toMap(
                    w -> w, w -> 1, Integer::sum))
            .entrySet()
            .stream()
            .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
            .limit(5)
            .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));

    }
}
