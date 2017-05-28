package tmp;

import java.util.ArrayList;
import static java.util.Arrays.asList;

/**
 * Created by kedar on 5/17/17.
 */
public class ArrayHashCode {
    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3};
        int[] b = new int[] {1, 2, 3};
        System.out.println(a.hashCode()); // did you mean to use Arrays.hashCode(a) instead?
        System.out.println(b.hashCode()); // did you mean to use Arrays.hashCode(b) instead?
        System.out.println(a.equals(b));
        ArrayList<Integer> l1 = new ArrayList<>(asList(1, 2, 3));
        ArrayList<Integer> l2 = new ArrayList<>(asList(1, 2, 3));
        System.out.println(l1.hashCode());
        System.out.println(l2.hashCode());
        System.out.println(l1.equals(l2));
    }
}
