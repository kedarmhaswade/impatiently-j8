package tmp;

import static java.util.Arrays.deepToString;

/**
 * Created by kmhaswade on 8/9/16.
 */
public class ArrayDemo {
    public static void main(String[] args) {
        Object[] a = new Object[1];
        a[0] = a;
        System.out.println(deepToString(a));
        niftyPrint(new int[]{1, 2, 3});
    }
    static void niftyPrint(int[] a) {
        int length;
        if (a == null || (length = a.length) == 0) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        for (int i = 0; ; i++) {
            System.out.print(a[i]);
            if (i == length - 1)
                break;
            System.out.print(", ");
        }
        System.out.println("]");
    }
}
