package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *     Given a string return a left-to-right, top-to-bottom reading of its sinusoidal
 *     writing. For example, given "hello world!", we can write it sinusoidally as:
 *       e       ␢       l
 *     h   l   o   w   r   d
 *           l       o
 *     and then read as: e␢lhlowrdlo.
 * </p>
 * Created by kmhaswade on 9/14/16.
 */
public class Sinusoidal {

    static String lrtb(String s) {
        List<StringBuilder> ss = new ArrayList<>(3);
        ss.add(new StringBuilder());
        ss.add(new StringBuilder());
        ss.add(new StringBuilder());
        int[] dir = new int[] {-1, 1, 1, -1};
        int li = 1; // the index of the list;
        int diri = 0; // the direction index
        int len = s.length();
        for (int i = 0; i < len; i++) {
            ss.get(li).append(s.charAt(i));
            li += dir[(diri++) % 4];
        }
        return ss.stream().collect(Collectors.joining());
    }
}
