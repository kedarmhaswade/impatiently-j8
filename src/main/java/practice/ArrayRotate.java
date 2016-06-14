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
        int n = a.length;
        k %= n; // modular arithmetic
        if (k == 0)
            return;
        int ii = 0; // final index
        int j;
        int i = 0;
        int t = a[i];
        while ((j = (i + k) % n) != ii) {
            //swap a[j] and t
            int tmp = t;
            t = a[j];
            a[j] = tmp;
            i = j;
        }
        a[j] = t;
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
