package practice;

import java.util.Arrays;

/**
 * <p>
 *     Quickly -- rotate a given array by k positions. This could be tricky if you are not prepared ...
 * </p>
 * Created by kmhaswade on 5/9/16.
 */
public class ArrayRotate {

    static void right(int[] a, int k) {
        // Bentley algorithm
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        right(a, 1);
        System.out.println(Arrays.toString(a));
        a = new int[] {3, 2, 3, 4, 4};
        right(a, 100);
        System.out.println(Arrays.toString(a));
        a = new int[] {1, 2, 3, 4};
        right(a, 2);
        System.out.println(Arrays.toString(a));
    }
}
