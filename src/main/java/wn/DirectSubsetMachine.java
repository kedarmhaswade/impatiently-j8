package wn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Implements a "subset machine" in that it returns an iterator of subsets of a given {@linkplain Iterable} of elements. <br/>
 * Note: The machine is <code>not</code> thread-safe.
 *
 * @param <T>
 */
public class DirectSubsetMachine<T> {

    private static final double L2 = Math.log(2);
    private final Iterable<T> set;
    private final int numSubsets;
    private int m = 0;

    /**
     * Constructs the subset machine with the help of given {@linkplain Iterable}.
     *
     * @param n   int the number of elements in the given Iterable. This provision is made so that the callers can a priori
     *            (without traversing the set) decide how many subsets they need, assuming enough elements are there in the set.
     *            It must be possible to iterate through n elements by the Iterable.
     * @param set Iterable that should not be structurally modified once its reference is passed here; results are
     *            unpredictable if this precondition is violated.
     */
    public DirectSubsetMachine(int n, Iterable<T> set) {
        this.numSubsets = (int) Math.pow(2, n);
        this.set = set;
    }

    /**
     * Returns an iterator that implements the Wilf-Nijenhuis algorithm named "direct approach":
     * <p>
     * To find the successor of a set S, insert the smallest element which is not in S, and delete
     * all the smaller elements from S.
     * </p>
     *
     * @return an Iterator of subsets of the given set.
     */
    public Iterator<Iterable<T>> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return DirectSubsetMachine.this.m < DirectSubsetMachine.this.numSubsets;
            }

            /**
             * Simply moves to the next element. There can be 2^n calls in all. The total running time (for the entire iteration)
             * is:
             * <p>
             *     lg(1) + lg(2) + ... + lg(2^n)
             * </p>
             * @return the next element
             */
            @Override
            public Iterable<T> next() {
                m += 1;
                Iterator<T> it = set.iterator(); // new iterator each time
                List<T> next = new ArrayList<>((int) (Math.log(m) / L2)); // it is okay to be imprecise
                int c = m % numSubsets;
                while (c > 0) {
                    T e = it.next(); // always progress the iterator, indirectly asserts hasNext
                    if ((c & 1) == 1) {
                        next.add(e);
                    }
                    c >>>= 1;
                }
                return next;
            }
        };
    }

    public static void main(String[] args) {
        List<String> set = Arrays.asList("Larry", "Moe", "Curly", "Me");
        DirectSubsetMachine<String> m = new DirectSubsetMachine<>(set.size(), set);
        Iterator<Iterable<String>> ssIter = m.iterator();
        while (ssIter.hasNext()) {
            System.out.println(ssIter.next());
        }
    }
}
