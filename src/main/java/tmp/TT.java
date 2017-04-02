package tmp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by kedar on 2/27/17.
 */
public class TT {
    public static ArrayList<Integer> x(ArrayList<Integer> A) {
        ArrayList<Integer> indices = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>(A);
        Collections.sort(A);
        int si = -1, ei = -1;
        for (int i = 0; i < A.size(); i++) {
            // there is a big fat bug here but it works!
            if (A.get(i) != B.get(i)) {
                if (si < 0) {
                    si = i;
                }
                ei = i;
            }
        }// end of for loop
        indices.add(si);
        indices.add(ei);
        return indices;
    }

    public static boolean areSame(List<Integer>a, List<Integer> b) {
        int len = a.size();
        if (len != b.size())
            return false;
        for (int i = 0; i < len; i++)
            if (a.get(i) != b.get(i))
                return false;
        return true;
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1100);
        a.add(1200);
        a.add(1400);
        a.add(1300);
        a.add(1500);
        System.out.println(x(a));
    }
//        Integer v1 = 43524;
//        Integer v2 = 43525;
//        System.out.println(v1 == v2 - 1);
//        Integer v3 = 43525 - 1;
//        System.out.println(v1 == v3);
//
//        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(34342));
//        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(42342));
//        System.out.println(Objects.equals(a.get(0), b.get(0)));
}
