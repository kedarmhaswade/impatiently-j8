package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Sam Blatherwick asked <a href="https://twitter.com/blatherwick_sam/status/1277484613431103498"> the following on Twitter</a></a>:
 * <p>
 * The product of three positive integers is 108. What is your systematic listing strategy to
 * ensure you get all possible sets?
 *
 * <p>
 * Then, I formulated a programming problem for my son (and anyone else):
 * <pre>
 *     Given a positive number p, find all the n-sets (n is given to be >= 1)
 *     such that the product of the members of each n-set is p.
 * </pre>
 * <p>
 * As the Samskritam verse, पुत्रादिच्छेत् पराजयम्।
 * (it roughly translates to "one should desire to be beaten by one's son"), suggests, <br/>
 * I am happy to report that he beat me at the solution. His solution is
 * <a href="https://github.com/vapporwashmade/java-projects/commit/fbcd152a38ea87dc9731d529b9e473c988e09671#diff-bf351e2b2cef56315c66405649fc7d57"> here</a>.
 * </p>
 */
public class ProductSets {

    public static void main(String[] args) {
        List<List<Integer>> nn = new ArrayList<>(10);
        get(1_000_000_000, 8, 1, nn);
//        get(4, 5, 1, nn);
        System.out.println(nn);
        System.out.println(nn.size());
    }

    public static void get(int p, int n, int first, List<List<Integer>> numbers) {
        if (n < 1)
            return; // do nothing
        if (n == 1) {
            if (numbers.isEmpty()) {
                numbers.add(new ArrayList<>());
            }
            for (List<Integer> list : numbers) {
                list.add(p);
            }
            return;
        }
        for (int i = first; i * i <= p; i++) {
            if (p % i != 0)
                continue;
            List<List<Integer>> nn = new ArrayList<>(10);
            get(p / i, n - 1, i, nn);
            for (List<Integer> smaller : nn) {
                List<Integer> newList = new ArrayList<>(16);
                newList.add(i);
                newList.addAll(smaller);
                numbers.add(newList);
            }
            nn = null; //naive help to GC
        }
    }
}
