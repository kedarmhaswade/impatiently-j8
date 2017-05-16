package practice;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *     Implements the Rabin-Karp algorithm to search a text for a given string. The main idea is that of a rolling
 *     hash calculation. Louis Wasserman <a href="http://stackoverflow.com/questions/12452527/rabin-karp-hashcode-is-too-big">
 *         suggests</a> that we do not need to bother with the modulus arithmetic at all. It all just works out
 *         (although I don't understand exactly how).
 * </p>
 * Created by kedar on 2/10/16.
 */
public class RabinKarp {

    private static final int BASE = 31;

    /**
     * Implements Rabin-Karp rolling hash algorithm, but suffers from excessive
     * number of string instances.
     * @param text string to search the pattern in
     * @param str pattern to search for
     * @return non negative integer denoting the first occurrence of str in text, -1 if there is no such occurrence
     */
    public static int index(String text, String str) {
        int fixed = hash(str); // let it overflow
        int i = 0, slen = str.length(), tlen = text.length();
        String s = text.substring(i, slen);
        int rolling = hash(s);
        int pow = pow(BASE, slen);
        while (true) {
            if (fixed == rolling) {
                if (str.equals(s)) {
                    return i;
                } else {
                    System.out.println("collision [" + rolling +  "]" + " found: str: " + str + ", s: " + s);
                }
            }
            i += 1;
            if (i + slen > tlen)
                return -1;
            char in = text.charAt(i + slen - 1);
            rolling = BASE * rolling - pow * s.charAt(0) + in;
//            rolling = (rolling - pow(BASE, slen - 1) * s.charAt(0)) * BASE + in; // this is fine too
            s = s.substring(1) + in;
        }
    }

    private static int pow(int x, int len) {
        int p = 1;
        for (int i = 0; i < len; i++)
            p *= x;
        return p;
    }
    private static int hash(String str) {
        int h = 0;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            h *= BASE;
            h += str.charAt(i);
        }
        return h;
    }

}
