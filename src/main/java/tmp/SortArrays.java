package tmp;

import java.util.*;

/**
 * Created by kmhaswade on 3/24/16.
 */
public class SortArrays {

    public static void main(String[] args) {
        List<int[]> listOfArrays = new ArrayList<>(4);
        listOfArrays.add(new int[]{0, 1, 4, 5});
        listOfArrays.add(new int[]{0, 0, 2, 3});
        listOfArrays.add(new int[]{0, 1, 1, 2});
        Collections.sort(listOfArrays, (o1, o2) -> {
            for (int i = 0; i < o1.length; i++) {
                if (o1[i] < o2[i])
                    return -1;
                if (o1[i] > o2[i])
                    return 1;
            }
            return 0;

        });
        listOfArrays.forEach(a -> System.out.println(Arrays.toString(a)));
    }
}
