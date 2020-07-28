package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Collections.*;

/**
 * <p>
 *     Robert Sedgewick's paper "Permutation Generation Methods" takes a fairly detailed overview of generating
 *     permutations. It pays to study that paper at least in parts. This should take the "fear" of generating
 *     permutations away. This class tries to implement a few methods of interest w.r.t. permutations. But like
 *     Sedgewick says in that paper, since dramatic changes are not expected in generating permutations, it's the
 *     processing of a particular permutation that matters.
 * </p>
 * Created by kmhaswade on 8/6/16.
 */
public class Permutations {

    /**
     * <p>A recursive implementation of finding permutations of elements in the given list. This takes into account
     * duplicates as well. The given list (src) is assumed to be sorted, if its elements are comparable.
     * </p>
     * @param dest the permutation
     */
    public static <T> void generatePermRecur(List<T> dest, List<T> src, Consumer<List<T>> sink) {
        if (src.isEmpty()) {
            sink.accept(dest); // the base case, dest is a perm of src
        } else {
            List<T> copy = new ArrayList<>(dest);
            int len = src.size();
            for (int i = 0; i < len; i++) {
                if (i == 0 || !src.get(i).equals(src.get(i - 1))) {
                    dest.add(src.get(i));
                    List<T> combined = new ArrayList<T>(len - 1);
                    combined.addAll(src.subList(0, i));
                    combined.addAll(src.subList(i + 1, len));
                    generatePermRecur(dest, combined, sink);
                    dest.clear();
                    dest.addAll(copy);
                }
            }
        }
    }

    public static <T> void printPermutations(List<T> sequence) {
        generatePermutationsUsingBasicExchangeNetwork(sequence, t -> System.out.println(t));
    }

    /**
     * <p>
     *     Sedgewick has introduced the idea of various exchange networks that that you repeatedly pass a sequence
     * through to generate the successor; stop when the beginning sequence reappears. Not all exchange networks
     * yield non-repeating sequences, but some do. This method uses the simplest one.
     * </p>
     * <p>
     *     TODO
     *     Need to find out answers to the following:
     *     <ul>
     *         <li> How many different exchange networks are there?</li>
     *         <li> Why do only some exchange networks produce non-repeating sequences?</li>
     *     </ul>
     * </p>
     * <p>
     *     The exchange network used in this method swaps elements in positions: (1, 2), (2, 3), ..., (n-1, n)
     *     before it rewinds to (1, 2). This continues till we have found all the n! permutations. To
     *     optimize, the number of permutations is calculated upfront. All elements of the list are assumed
     *     unique. <b> Note: This is an iterative algorithm, the permutations are not sorted sequences. </b>
     * </p>
     * @param sequence list of elements to permute
     * @param consumer consumes a new sequence
     * @param <T> the type of elements of the sequence
     */
    public static <T> void generatePermutationsUsingBasicExchangeNetwork(List<T> sequence, Consumer<List<T>> consumer) {
        int size = sequence.size();
        if (size <= 1) {
            consumer.accept(sequence);
            return;
        }
        long fact = f(size);
        int fi = 0;
        for (int i = 1; i <= fact; i++) {
            consumer.accept(sequence);
            if (! sequence.get(fi).equals(sequence.get(fi + 1))) // swap only if the elements are different
                swap(sequence, fi, fi + 1);
            fi = (fi + 1) % (size - 1);
        }
    }

    /**
     * <p>
     *     Given a sequence of comparable items, this method generates the <i>next</i> permutation, using the natural
     *     order of its elements. If the given sequence is already reverse sorted, it returns an empty list.
     * </p>
     * @param perm
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> List<T> nextPermutation(List<T> perm) {
        int size = perm.size();
        if (size <= 1)
            return emptyList();
        int i = size - 2;
        while (i >= 0 && perm.get(i).compareTo(perm.get(i + 1)) > 0)
            i -= 1;
        if (i == -1)
            return emptyList();
        i += 1;
        // now i is the index of the element where the decreasing sub-list starts.
        int j;
        for (j = i; j < size; j++)
            if (perm.get(j).compareTo(perm.get(i - 1)) < 0)
                break;
        j -= 1;
        // now j is the index of the element that is largest element smaller than i - 1 th
        Collections.swap(perm, i - 1, j);
        reverse(perm.subList(i, size));
        return perm;
    }

    /**
     * Generics are great, but they yield an awkward syntax. The algorithm to solve a problem is always
     * independent of the generic type; so this method demonstrates the saving in time owing to writing non-generic
     * algorithms when you are pressed for time.
     * <p>
     *     In a naturally ordered list of all permutations of given numbers, this method returns a permutation that
     *     comes before the given permutation. If there is no such permutation (i.e. the given permutation is an
     *     identity permutation), then this method returns an empty list.
     * </p>
     * @param perm a permutation of integers. All integers are distinct.
     * @return the previous permutation of the same integers.
     */
    static List<Integer> previousPermutation(List<Integer> perm) {
        int size = perm.size();
        if (size <= 1)
            return emptyList();
        // find the lexically ordered suffix
        int i = size - 2;
        while (i >= 0 && perm.get(i) < perm.get(i + 1))
            i -= 1;
        if (i == -1)
            return emptyList(); // the given permutation is the identity permutation
        i += 1; // i = index of the subList where increasing suffix starts
        // find in this suffix the index of the largest number that is smaller than perm.get(i-1)
        int j = i;
        while (j < size && perm.get(j) < perm.get(i - 1))
            j++;
        j -= 1;
        swap(perm, i - 1, j);
        reverse(perm.subList(i, size));
        return perm;
    }
    static long f(int n) {
        long nf = 1;
        for (int i = 1; i <= n; i++)
            nf *= i;
        return nf;
    }
}
