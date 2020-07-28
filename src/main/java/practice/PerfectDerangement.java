package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/**
 * Heinrich Dörrie asks this Bernoulli-Euler problem of Misaddressed Letters:
 * <p>
 *     Someone writes n letters and writes the corresponding addresses on n envelopes. How
 *     many different ways are there of placing all the letters in the wrong envelopes?
 * </p>
 * <p>
 *     One should solve this problem mathematically, but as I struggled with it, I thought that I should
 *     check a few things with the help of a program.
 * </p>
 */
public class PerfectDerangement {
    private static int atleastOneMatch = 0;
    private static int noMatch = 0;
    public static void main(String[] args) {
        Permutations.generatePermRecur(new ArrayList<>(5), Arrays.asList(1, 2, 3, 4), PerfectDerangement::registerMatches);
        out.println("at least one match: " + atleastOneMatch);
        out.println("no match: " + noMatch);
    }
    public static void registerMatches(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int i1 = i + 1;
            if (list.get(i) == i1) {
                atleastOneMatch += 1;
//                System.out.println("match at index: " + i1 + " for: " + list);
                return; // must stop at the first match
            }
        }
        noMatch += 1;
        out.println("no match for: " + list);
    }
}