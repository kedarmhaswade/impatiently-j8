package tmp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.lines;

/**
 * Created by kmhaswade on 3/21/16.
 */
public class SummaryStatsEx {
    public static void main(String[] args) throws IOException {
        String cwd = System.getProperty("user.dir");
//        System.out.println(cwd);
        // first attempt
        long loc = Files.walk(Paths.get(cwd), 10)
                .filter(p -> p.toString().endsWith(".java"))
//                .peek(p -> System.out.println(p))
                .flatMap(p -> {
                    try {
                        return lines(p);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return Stream.empty();
                    }
                })
                .filter(s -> !s.startsWith("//"))
                .filter(s -> s.length() > 2)
                .count();
//                .collect(Collectors.summarizingLong(s -> 1))
//                .getSum();
        System.out.println("Java lines of code: " + loc);

        // second attempt
        LongSummaryStatistics longStat = Files.walk(Paths.get(cwd), 10)
                .filter(p -> p.toString().endsWith(".java"))
                .collect(Collectors.summarizingLong(p -> {
                    try {
                        return lines(p)
                                .filter(s -> !s.startsWith("//"))
                                .filter(s -> s.length() > 2)
                                .count();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }));
        System.out.println("total lof: " + longStat.getCount());
        System.out.println("total loc: " + longStat.getSum());
        System.out.println("avg loc: " + longStat.getAverage());
        System.out.println("max # lines per file: " + longStat.getMax());
        System.out.println("min # lines per file: " + longStat.getMin());
    }

}
