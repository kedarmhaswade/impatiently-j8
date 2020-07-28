package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Sam Blatherwick asked <a href="https://twitter.com/blatherwick_sam/status/1277484613431103498"> the following on Twitter</a></a>:
 * <p>
 * The product of three positive integers is 108. What is your systematic listing strategy to
 * ensure you get all possible sets?
 *
 * <p>
 * Then, I formulated a programming problem for my son (and anyone else):
 * <pre>
 Given a positive number p, find all the unique n-sequences of
 integers (n is given to be >= 1) such that the product of the elements of
 each n-sequence is p. No two sequences may have the same elements in any order.
 * </pre>
 * <pre>
 * As the Samskritam verse, पुत्रादिच्छेत् पराजयम्।
 * (it roughly translates to "one should desire to be beaten by one's son"), suggests, <br/>
 * I am happy to report that he beat me at the solution. His solution is
 * <a href="https://github.com/vapporwashmade/java-projects/commit/fbcd152a38ea87dc9731d529b9e473c988e09671#diff-bf351e2b2cef56315c66405649fc7d57"> here</a>.
 * <br/> The only solace is that my program appears to run faster than his.
 * </pre>
 */
public class ProductSequences {

    public static void main(String[] args) {
        List<List<Integer>> sequences = new ArrayList<>(10);
        get(108, 3, 1, new ArrayList<>(), sequences);
        System.out.println(sequences);
        System.out.println(sequences.size());
    }

    public static void get(int p, int n, int first, List<Integer> sequence, List<List<Integer>> sequences) {
        if (n < 1)
            return; // do nothing
        if (n == 1) {
            sequence.add(p); // ignore return value
            sequences.add(sequence); //ignore return value
            return;
        }
        for (int i = first; i * i <= p; i++) {
            if (p % i == 0) {
                List<Integer> ns = new ArrayList<>(sequence);
                ns.add(i); // ignore return value
                get(p / i, n - 1, i, ns, sequences);
            }
        }
    }
}
