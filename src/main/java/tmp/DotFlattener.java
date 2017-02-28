package tmp;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.of;

/**
 * Created by kedar on 2/17/17.
 */
public class DotFlattener {

    private static String joinWithDots(String first, String... remaining) {
        if (first == null)
            return null; // decide if you want to return ""
        if (remaining == null || remaining.length == 0)
            return first.trim();
        return concat(of(first.trim()), stream(remaining).map(String::trim)).collect(joining("."));
    }

    public static void main(String[] args) {
//        System.out.println(joinWithDots()); // note: by requiring at least one arg, this results in compile error!
        System.out.println(joinWithDots("a"));
        System.out.println(joinWithDots("a", "b"));
        System.out.println(joinWithDots("  a.b  ", "c"));
    }
}
