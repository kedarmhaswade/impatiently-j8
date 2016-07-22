package practice;

/**
 * <p>
 *     Given an array, find the length of longest subarray all of whose entries are equal.
 * </p>
 * Created by kmhaswade on 7/18/16.
 */
public class LongestEqualSubarray {

    static int longestSubarray(int[] a) {
        int current = 1;
        int longest = current;
        for (int i = 1; i < a.length; i++) {
            current = a[i] == a[i-1] ? current + 1 : 1;
            longest = Math.max(current, longest);
        }
        return longest;
    }
}
