package org.kedar.java.reallyimp.ch1;

import java.util.Arrays;

/** In Chapter 1 Exercises, a question is asked:
 * <p>
 *     Does the comparator code in the Arrays.sort method run in the same thread as the thread where the call to sort runs?
 * </p>
 * <p>
 *     This class intends to provide an answer. It appears that the thread calling the sort method should
 *     run in the same thread as the sort method itself because sort calls the given comparator that compares the elements
 *     of the given array. For, if it were not, then the call to Arrays.sort would return to its caller, while
 *     the sorting/comparisons may be going on in another thread. This may lead to an inconsistency.
 *     Thus, comparison code must be a part of the sorting operation.
 * </p>
 * <p>
 *     I thought of looking at the names of the threads in which the sort method is called and the compare method is called.
 * </p>
 * Created by kmhaswade on 2/19/16.
 */
public class ComparatorThreadP1 {
    public static void main(String[] args) {
        String[] planets = new String[]{"mercury", "venus", "earth", "jupiter", "mars", "saturn", "uranus", "neptune", "pluto"};
        System.out.println("Calling Thread: " + Thread.currentThread());
        Arrays.sort(planets, (p1, p2) -> {
            // this is the comparison code
            System.out.println("Comparison code runs in: " + Thread.currentThread());
            return p1.compareTo(p2);
        });
        System.out.println(Arrays.toString(planets));
    }
}
