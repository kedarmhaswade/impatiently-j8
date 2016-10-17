package practice.hr;

/**
 * <p>
 *     Basic implementation of the insertion sort.
 * </p>
 * Created by kedar on 1/10/16.
 */
public class Insertion {

    int[] iSort(int[] a) {
        // in-place insertion sort
        int len = a.length;
        for (int i = 1; i <= len - 1; i++) {
            int x = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > x)
                    a[j + 1] = a[j];
            }
            a[j + 1] = x;
        }
        return a;
    }
}
