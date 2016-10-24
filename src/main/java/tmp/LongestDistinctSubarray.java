package tmp;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * An attempt to solve <a href="http://stackoverflow.com/questions/35882366/find-the-longest-subarray-with-distinctive-entries">
 *     this problem</a>
 *     {@linkplain #longestSubarray(Object[])} is an interesting O(n) algorithm to do this.
 * <p>Created by kmhaswade on 3/8/16.
 * </p>
 */
public class LongestDistinctSubarray {

    public static <T> int[] longestSubarray(T[] arr) {
        int i = 0, j = 1;
        int[] maxDistinct = new int[] {i, j - 1};
        Set<T> set = new HashSet<>();
        set.add(arr[0]); // ignore return
        int currLen = 1, max = 1; // the first element is always distinct

        int length = arr.length;
        while (j < length) {
            if (!set.contains(arr[j])) {
                currLen++;
                boolean added = set.add(arr[j++]);
                assert added;
            }
            else {
                set.remove(arr[i++]);
                currLen--;
            }
            if (currLen > max) {
                max = currLen;
                maxDistinct[0] = i;
                maxDistinct[1] = j - 1;
            }
        }

        return maxDistinct;
    }
    public static void main(String[] args) {
//        int[] a = new int[] {1,2,3,4,2,5,6};
        Integer[] a = new Integer[] {1, 1, 2, 2};
        System.out.println(Arrays.toString(longestSubarray(a)));
    }
}
