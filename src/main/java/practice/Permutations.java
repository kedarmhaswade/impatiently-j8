package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Collections.*;
import static java.util.stream.Collectors.groupingBy;

/**
 * <p>
 * Robert Sedgewick's paper "Permutation Generation Methods" takes a fairly detailed overview of generating
 * permutations. It pays to study that paper at least in parts. This should take the "fear" of generating
 * permutations away. This class tries to implement a few methods of interest w.r.t. permutations. But like
 * Sedgewick says in that paper, since dramatic changes are not expected in generating permutations, it's the
 * processing of a particular permutation that matters.
 * </p>
 * Created by kmhaswade on 8/6/16.
 */
public class Permutations {

    /**
     * <p>A recursive implementation of finding permutations of elements in the given list. This takes into account
     * duplicates as well. The given list (src) is assumed to be sorted, if its elements are comparable.
     * </p>
     *
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
     * Sedgewick has introduced the idea of various exchange networks that that you repeatedly pass a sequence
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
     *
     * @param sequence list of elements to permute
     * @param consumer consumes a new sequence
     * @param <T>      the type of elements of the sequence
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
            if (!sequence.get(fi).equals(sequence.get(fi + 1))) // swap only if the elements are different
                swap(sequence, fi, fi + 1);
            fi = (fi + 1) % (size - 1);
        }
    }

    /**
     * <p>
     * Given a sequence of comparable items, this method generates the <i>next</i> permutation, using the natural
     * order of its elements. If the given sequence is already reverse sorted, it returns an empty list.
     * </p>
     *
     * @param perm
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> List<T> nextPermutation(List<T> perm) {
        int size = perm.size();
        if (size <= 1) return emptyList();
        int i = size - 2;
        while (i >= 0 && perm.get(i).compareTo(perm.get(i + 1)) > 0) i -= 1;
        if (i == -1) return emptyList();
        i += 1;
        // now i is the index of the element where the decreasing sub-list starts.
        int j;
        for (j = i; j < size; j++)
            if (perm.get(j).compareTo(perm.get(i - 1)) < 0) break;
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
     * In a naturally ordered list of all permutations of given numbers, this method returns a permutation that
     * comes before the given permutation. If there is no such permutation (i.e. the given permutation is an
     * identity permutation), then this method returns an empty list.
     * </p>
     *
     * @param perm a permutation of integers. All integers are distinct.
     * @return the previous permutation of the same integers.
     */
    static List<Integer> previousPermutation(List<Integer> perm) {
        int size = perm.size();
        if (size <= 1) return emptyList();
        // find the lexically ordered suffix
        int i = size - 2;
        while (i >= 0 && perm.get(i) < perm.get(i + 1)) i -= 1;
        if (i == -1) return emptyList(); // the given permutation is the identity permutation
        i += 1; // i = index of the subList where increasing suffix starts
        // find in this suffix the index of the largest number that is smaller than perm.get(i-1)
        int j = i;
        while (j < size && perm.get(j) < perm.get(i - 1)) j++;
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

    /**
     * <p>
     * Returns the k-tuples of a given set.</p>
     * <p>
     * Ordered arrangements of k elements of a set S, where repetition is allowed, are called its k-tuples.
     * If the set S has n elements, the number of k-tuples over S is n^k.
     * </p>
     *
     * @param k   the length of each tuple
     * @param set {@linkplain List} of objects to choose from; we use a List for a reliable iteration order
     * @param <T> type of element in the set and in each k-tuple
     * @return A {@linkplain List<List<T>>} whose elements are k-tuples. Elements of each k-tuple are members of the given {@code set}
     * @see <a href="https://en.wikipedia.org/wiki/Permutation#Permutations_with_repetition">https://en.wikipedia.org/wiki/Permutation#Permutations_with_repetition</a>
     */
    public static <T> List<List<T>> kTuples(int k, List<T> set) {
        return orderedArrangements(k, set, true);
    }

    /**
     * <p>
     * Returns all arrangements of members of the given set in <code>k</code> places linearly. If <code>rep</code> is <code>true</code>,
     * set members may repeat in arrangements. For example, <code>[0, 0], [1, 1]</code> etc. are valid 2-arrangements of members
     * of the set of decimal digits: <code>[0, 1, ..., 9]</code> in 2 places.
     * </p>
     *
     * @param k   the integer representing the number of places
     * @param set the Set whose elements are to be placed
     * @param rep decides whether an element can occur more than once in an arrangement
     * @param <T>
     * @return a list of all the arrangements
     */
    public static <T> List<List<T>> orderedArrangements(int k, List<T> set, boolean rep) {
        if (k <= 0) {
            return singletonList(emptyList()); // there's one way to arrange: don't place any member anywhere
        }
        if (k > set.size() && !rep) {
            return emptyList(); // the set is too short; there is no way to arrange its members in available places without repetition
        }
        List<List<T>> arrs = set.stream().map(e -> List.of(e)).collect(Collectors.toList());//new ArrayList<>(set.stream().collect(groupingBy(e -> e)).values());
        for (int i = 1; i < k; i++) {
            List<List<T>> aug = new ArrayList<>();
            arrs.forEach(partial -> {
                for (T e : set) {
                    if (rep || !partial.contains(e)) {
                        List<T> cp = new ArrayList<>(partial);
                        cp.add(e);
                        aug.add(cp);
                    }
                }
            });
            arrs = aug;
        }
        // assert arrs.size() == Math.pow(List.size(), k) : "size must be: " + Math.pow(List.size(), k); // only if rep is false
        return arrs;
    }


    /**
     * <p>
     * According to Wikipedia, in elementary combinatorics, the <b><code>k-permutations,</code></b>or partial
     * permutations, of an <b><code>n-set</code></b> are the ordered arrangements of <code>k</code> distinct elements selected
     * from the set.
     * </p>
     * <p>
     * An example return value for <code>k=3, n=7</code> could be:
     * <code>
     * <pre>
     *         [
     *           [0, 1, 2],
     *           [0, 1, 3],
     *           ...
     *           [1, 2, 3]
     *           ...
     *           [3, 2, 4]
     *           ...
     *           [4, 5, 6]
     *           ...
     *         ]
     *     </pre>
     * </code>
     * </p>
     * <p>
     * One can easily use each element of the returned list as a list of indices to filter, map, and collect the actual
     * elements:
     * </p>
     * <code>
     * List<String> names = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie", "David", "Emma")); // a set of names
     * <p>
     * // Indices to collect
     * int[] indices = kPermutationIndices(3, set.size())[0]; // => [1, 3, 4] (say)
     * <p>
     * // Using IntStream and List.get()
     * <pre>
     * List&lt;String> result = IntStream.range(0, names.size())
     * .filter(indices::contains)
     * .mapToObj(names::get)
     * .collect(Collectors.toList()); // => ["Bob", "David", "Emma"], one 3-permutation of names which is a 5-set
     * </pre>
     * </code>
     *
     * @param n the number of elements to arrange
     * @param k the number of elements from the set to arrange (may not be negative or greater than <code>n</code>)
     * @return an array of all k-permutations of the set each element of which is a k-array whose elements are indices of
     * members of the n-set arranged in that permutation. The number of <code>k-permutations</code> of an
     * <code>n-set</code> is <code>n!/(n-k)!</code>
     * <p>
     * All the indices <code>idx</code> in the returned lists are such that <code> 0 <= idx < n</code>
     * </p>
     */
    public static int[][] nPk(int n, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Can't choose negative number of elements: " + k);
        }
        if (k > n) {
            return new int[0][]; //nPr = 0 when k > n: https://en.wikipedia.org/wiki/Permutation#k-permutations_of_n
        }
        int[][] kperms = nPk(n, k, 0);
        assert kperms.length == calculateNPK(n, k) : n + "P" + k + ": exp: " + calculateNPK(n, k) + ", actual: " + kperms.length;
        return kperms;
    }

    private static int[][] nPk(int n, int k, int begIndex) {
        if (k == 0) {
            return new int[1][0]; //nPn = 1
        } else {
            List<int[]> bigs = new ArrayList<>();
            for (int i = begIndex; i <= n - k; i++) {
                int[][] smalls = nPk(n, k - 1, i + 1);
                augment(smalls, i, bigs);
            }
            return bigs.toArray(new int[bigs.size()][]);
        }
    }

    private static void augment(int[][] smalls, int i, List<int[]> bigs) {
        for (int[] small : smalls) {
            int slen = small.length;
            for (int j = 0; j <= slen; j++) {
                int[] big = new int[slen + 1];
                System.arraycopy(small, 0, big, 0, j);
                big[j] = i;
                System.arraycopy(small, j, big, j + 1, slen - j);
                bigs.add(big);
            }
        }
    }

    public static final long calculateNPK(int n, int k) {
        if (n < 0 || k < 0) {
            throw new IllegalArgumentException("either or both arguments negative");
        }
        // not special casing a rare condition k > n; the following correctly evaluates it to 0
        long result = 1L;
        for (int i = 0; i <= k - 1; i++) {
            result = Math.multiplyExact(result, (n - i)); // detects overflow
        }
        return result;
    }
}
