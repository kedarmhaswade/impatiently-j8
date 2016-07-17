package tmp;

import java.util.HashSet;

/**
 * Created by kmhaswade on 7/15/16.
 */
public class SetOfArrays {
    public static void main(String[] args) {
        HashSet<int[][]> x = new HashSet<>();
        int a[][] = {{1, 2}, {3, 4}};
        x.add(a);
        int b[][] = {{1, 2}, {3, 4}};
        System.out.println(x.contains(a));
        System.out.println(x.contains(b));
    }
}
