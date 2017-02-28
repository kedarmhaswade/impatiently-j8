package tmp;

import static java.util.Arrays.binarySearch;

/**
 * Created by kedar on 2/16/17.
 */
public class BinSearchEx {
    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 8, 10};
        System.out.println(binarySearch(a, 0, a.length, 3));
        a = new int[] {1, 2, 2, 2, 2, 8, 10};
        System.out.println(binarySearch(a, 0, a.length, 2));
    }
}
