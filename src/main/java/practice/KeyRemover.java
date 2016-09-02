package practice;

/**
 * <p>
 *     A program to take an array and a key and remove all occurrences of the key from the array. All the
 *     elements are shifted to left to fill the possible holes. You can set the value of the invalid elements
 *     (i.e. the one that are same as key) to null. Returns the number of remaining elements.
 * </p>
 * Created by kmhaswade on 9/1/16.
 */
public class KeyRemover {

    static <T> int remove(T[] a, T key) {
        int i = 0;  // read
        int j = 0;  // write
        int len = a.length;
        while (i < len) {
            if (!a[i].equals(key)) {
                swap(i, j, a);
                j += 1;
            }
            i += 1;
        }
        int n = j;
        while (j < len)
            a[j++] = null;
        return n;
    }

    private static <T> void swap(int i, int j, T[] a) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
