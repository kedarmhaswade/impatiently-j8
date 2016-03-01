package tmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** <p>
 *      Find the lucky numbers amongst natural numbers from 1 to n.
 *      Here's how you find <a href="http://stackoverflow.com/questions/35673769/java-program-to-find-lucky-numbers-from-0-to-n-using-lists">Lucky numbers</a>.
 * </p>
 * Created by kmhaswade on 2/27/16.
 */
public class Lucky {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        List<Integer> n1 = printLucky1(Integer.valueOf(args[0]));
        System.out.println("time taken: " + (System.currentTimeMillis() - t1));
        t1 = System.currentTimeMillis();
        List<Integer> n2 = printLucky2(Integer.valueOf(args[0]));
        System.out.println("time taken: " + (System.currentTimeMillis() - t1));
        assert n1.equals(n2) : "oops, lists are not the same!";
    }
    private static List<Integer> printLucky1(int n) {
        List<Integer> numbers = Stream.iterate(1, k -> k + 1).limit(n).collect(Collectors.toList());
//        System.out.println(numbers);
        int delIndex = 1; // index of the element to be removed, we start with 2nd element
        while (delIndex < numbers.size()) {
            List<Integer> deleted = new ArrayList<>();
            for (int i = delIndex; i < numbers.size(); i += (delIndex + 1)) {
                deleted.add(numbers.get(i));
            }
            numbers.removeAll(deleted); // expensive operation!
//            System.out.println(numbers);
            delIndex += 1;
        }
        System.out.println("number of lucky numbers:" + numbers.size());
        System.out.println(numbers);
        return numbers;
    }
    private static List<Integer> printLucky2(int n) {
        List<Integer> numbers = Stream.iterate(1, k -> k + 1).limit(n).collect(Collectors.toList());
        System.out.println(numbers);
        int delIndex = 1; // index of the element to be removed, we start with 2nd element
        while (delIndex < numbers.size()) {
            List<Integer> retained = new ArrayList<>();
            for (int i = 0; i < numbers.size(); i += 1)
                if ((i+1) % (delIndex + 1) != 0)
                    retained.add(numbers.get(i));
            numbers = retained;
//            System.out.println(numbers);
            delIndex += 1;
        }
        System.out.println("number of lucky numbers:" + numbers.size());
        System.out.println(numbers);
        return numbers;
    }

}
