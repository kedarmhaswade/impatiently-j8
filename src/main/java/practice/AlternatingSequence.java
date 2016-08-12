package practice;

import java.util.List;

import static java.util.Collections.swap;

/**
 * <p>
 *     Given a list a of numbers, rearrange the list such that a[0] &lt;= a[1] >= a[2] &lt;= a[3] ...
 * </p>
 * <p>
 *     There are several algorithms to solve this problem, but iterative improvement suggests the following very
 *     interesting O(n) time and O(1) space algorithm: swap the elements at even and odd indexes that fail to satisfy
 *     the condition. I am still looking for the proof of its correctness.
 * </p>
 * Created by kmhaswade on 8/11/16.
 */
public class AlternatingSequence {

    static <T extends Comparable<T>> void arrange(List<T> list) {
        int size = list.size();
        for (int i = 1; i < size; i++) {
            if ((i % 2 != 0 && list.get(i).compareTo(list.get(i-1)) < 0) ||
                (i % 2 == 0 && list.get(i).compareTo(list.get(i-1)) > 0))
                swap(list, i - 1, i);
        }
    }
}
