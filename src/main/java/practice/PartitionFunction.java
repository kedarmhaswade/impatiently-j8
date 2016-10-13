package practice;

import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * Given an integer, write code to create a partition function such that all partitions of that integer
 * are created.
 * </p>
 * This
 * Created by kedar on 10/11/16.
 */
public class PartitionFunction {

    /**
     * Uses a non-increasing partitioning.
     * For example, if a non-increasing partition function would partition the integer
     * <code>5</code> exactly into following seven partitions:
     * <ul>
     * <li>5</li>
     * <li>4, 1</li>
     * <li>3, 2</li>
     * <li>3, 1, 1</li>
     * <li>2, 2, 1</li>
     * <li>2, 1, 1, 1</li>
     * <li>1, 1, 1, 1, 1</li>
     * </ul>
     * <p>
     * This should be called as: <code>nonIncreasing(n, mutableLinkedList, mutableList);</code>
     * </p>
     *
     * @param n          the given integer
     * @param rem        remaining parts, each of which is either smaller than or equal to n
     * @param partitions all the partitions
     */
    public static void nonIncreasing(int n, LinkedList<Integer> rem, List<List<Integer>> partitions) {
        if (n >= 1) { // simplest base case
            LinkedList<Integer> extracted = new LinkedList<>();
            extracted.addFirst(n);
            extracted.addAll(rem);
            boolean a = partitions.add(extracted);
            assert a;
            int first = n - 1;
            while (first >= 1) {
                int second = n - first;
                if (first < second)
                    break;
                LinkedList<Integer> remCopy = new LinkedList<>(rem);
                Integer head = remCopy.peek();
                if (remCopy.isEmpty() || (second >= head)) { // preserve the order
                    remCopy.addFirst(second);
                    nonIncreasing(first, remCopy, partitions);
                }
                first -= 1;
            }
        }
    }
}