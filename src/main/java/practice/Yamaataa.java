package practice;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.Collections.emptyList;

/**
 * In Sanskrit/Marathi prosody, a group (gaNa) is made of three letters each of which can be either short (laghu)
 * or long (guruu). Thus, a group is a binary 3-string like 011.
 * <p>
 * Device all the binary 10-strings which can be traversed from left to write in 3's such that all the eight
 * binary 3-strings are covered exactly once by shifting the 3-bit window by one bit at a time.
 * </p>
 * <p>
 * For example, the binary 10-string <code>0111010001</code> covers all of
 * <code> 011, 111, 110, 101, 010, 100, 000, 001 </code>
 * exactly once when traversed this way.
 * </p>
 */
public class Yamaataa {
    private static final List<String> SINGLETON_LIST_OF_EMPTY_STRING = List.of("");
    public static final List<Character> CS = List.of('0', '1');

    private static final Map<String, String> NAMES = new HashMap<>(8);
    static {
        NAMES.put("011", "यमाता");
        NAMES.put("111", "मातारा");
        NAMES.put("110", "ताराज");
        NAMES.put("101", "राजभा");
        NAMES.put("010", "जभान");
        NAMES.put("100", "भानस");
        NAMES.put("000", "नसल");
        NAMES.put("001", "सलगं");
    }
    public static void main(String[] args) {
        findYamaataas();
    }

    private static void findYamaataas() {
        List<String> tenStrings = allStrings(CS, 10);
        int y = 0;
        for (String ts : tenStrings) {
            if (isYamaataa(ts)) {
                y += 1;
                System.out.println(ts);
            }
        }
        System.out.println("# yamaataas: " + y);
    }

    /**
     * Determines if the given binary 10-string is of the form Yamaataa.
     * @param ts a binary 10-string -- must have 10 binary characters.
     * @return
     */
    private static boolean isYamaataa(String ts) {
        Set<String> g = new HashSet<>(Set.of("000", "001", "010", "011", "100", "101", "110", "111"));
        streamStrings(ts, 3, 1).limit(10).forEach(g::remove);
        return g.isEmpty();
    }

    /**
     * Streams strings of length up to len. Each streamed string is created from the string src by removing
     * first "shift" characters and adding last "shift" characters. The shifting starts from 2nd string onward. It's
     * like traversing the given string by a fixed length frame that shifts forward "shift" characters at a time.
     *
     * @param src   the source string, e.g. "abcde"
     * @param len   max length of the string to be streamed, no string streamed from this method will exceed this length, e.g. 2
     * @param shift integer shift-width to traverse src, e.g. 1
     * @return a Stream of strings, e.g. the above will stream "ab", "bc", "cd", and "de"
     */
    private static Stream<String> streamStrings(String src, int len, int shift) {
        Iterator<String> traverser = new Iterator<>() {
            private int i = 0;
            private int j = Math.min(len, src.length()); // substring(i, j) is what we need

            @Override
            public boolean hasNext() {
                return i < j && j <= src.length();
            }

            @Override
            public String next() {
                String n = src.substring(i, j);
                i += shift;
                if (j < src.length())
                    j = Math.min(i + len, src.length());
                else
                    j = i + len;
                return n;
            }
        };
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(traverser, Spliterator.SIZED), false);
    }
    /**
     * Generates a list of n-strings each character of which is one of the given list of characters
     *
     * @param cs list of (preferably unique) characters, e.g. ['0', '1', '2']
     * @param n  length of each string made of characters from cs, e.g. 2
     * @return list (size = cs.size() ^ n) of n-strings, e.g. ["00", "01", "02", ..., "22"] containing (3^2 =) 9 strings
     */
    private static List<String> allStrings(List<Character> cs, int n) {
        // base: anything ^ 0 = 1; there is one string of length 0 -- the empty string
        if (n == 0) return SINGLETON_LIST_OF_EMPTY_STRING;
        // base: there is no string of characters if there are no characters
        if (cs.size() == 0) return emptyList();
        List<String> prev = allStrings(cs, n - 1);
        List<String> nss = new ArrayList<>(prev.size() * cs.size());
        for (char c : cs) {
            for (String p : prev) {
                nss.add(c + p);
            }
        }
        return nss;
    }

}
