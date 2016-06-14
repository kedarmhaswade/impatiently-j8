package tmp;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by kmhaswade on 5/9/16.
 */
public class Sorting {
    static int[] getSortedLengths(String[] names) {
        int len = names.length;
        int [] lengths = new int[len];
        for (int i = 0; i < len; i++) {
            // watch for nulls
            lengths[i] = names[i].length();
        }
        Arrays.sort(lengths);
        return lengths;
    }
    static void sortStringsByLength(String[] names) {
        Comparator<String> stringLengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
        Arrays.sort(names, stringLengthComparator);
    }
}
