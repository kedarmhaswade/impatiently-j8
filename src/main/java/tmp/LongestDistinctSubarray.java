package tmp;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * An attempt to solve <a href="http://stackoverflow.com/questions/35882366/find-the-longest-subarray-with-distinctive-entries">
 *     this problem</a>
 *     {@linkplain #longestSubarray(int[])} is an interesting O(n) algorithm to do this.
 * Created by kmhaswade on 3/8/16.
 */
public class LongestDistinctSubarray {

    public static int longestSubarray(int[] arr) {
        int i = 0, j = 1, max = 0, currLength = 1;
        HashSet<Integer> set = new HashSet<Integer>();
        set.add(arr[0]);

        while (i < arr.length - 1 && j < arr.length) {
            if (!set.contains(arr[j])) {
                currLength++;
                set.add(arr[j++]);
            }
            else {
                max = Math.max(max, currLength);
                set.remove(arr[i++]);
                currLength--;
            }
        }

        return Math.max(currLength, max);
    }
    public static void main(String[] args) {
//        int[] a = new int[] {1,2,3,4,2,5,6};
        int[] a = new int[] {1, 1, 2, 2};
        System.out.println(longestSubarray(a));
    }
}
