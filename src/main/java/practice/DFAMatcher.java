package practice;

import util.MyDebug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** Creates a DFA from a given pattern and searches a given string for the existence of this
 * pattern.
 * Created by kmhaswade on 5/31/16.
 */
public class DFAMatcher {
    public static final String GENOMIC_ALPHABET = "ACGT";

    private final String pattern;
    private final String alphabet;
    private final int[][] states;

    public DFAMatcher(String pattern, String alphabet) {
        //validate
        this.pattern = pattern;
        this.alphabet = alphabet;
        int plen = pattern.length(); // length of pattern
        int alen = alphabet.length(); // number of alphabets
        states = new int[plen + 1][alen + 1];
        // 0th column is the same as the row id for each row
        for (int i = 0; i < states.length; i++)
            states[i][0] = i;
        // fill the remaining columns for each row (except last) of the DFA matrix
        for (int i = 0; i <= plen; i++) {
            for (int j = 0; j < alphabet.length(); j++) {
                int col = j + 1;
//                util.MyDebug.println("examining char: " + alphabet.charAt(j));
                if (i < plen && pattern.charAt(i) == alphabet.charAt(j)) {
                    states[i][col] = i + 1;
                } else {
                    String candidate = pattern.substring(0, i) + alphabet.charAt(j);
                    int lps = longestProperPrefixSuffixLength(candidate);
                    states[i][col] = lps;
//                    util.MyDebug.println("candidate: " + candidate + " [" + i + ", " + col + "] = " + lps);
                }
            }
        }
        // now, the last row has to be exactly the same as the first row
//        System.arraycopy(states[0], 0, states[plen], 0, states[0].length);
        print();
    }

    private void print() {
        for (int i = 0; i < states.length; i++) {
            MyDebug.print("[");
            MyDebug.print(
                    Arrays.stream(states[i])
//                    .peek(j -> util.MyDebug.println(j))
                    .mapToObj(j -> "" + j)
                    .collect(Collectors.joining(", "))
            );
            MyDebug.println("]");
        }
    }

    public List<Integer> matches(String text) {
//        return this.matches(text.chars());
        List<Integer> m = new ArrayList<>();
        int plen = pattern.length();
        int state = 0; // the start state
        int index = 0;
        for (int c : text.toCharArray()) {
            int col = genomicIndex(c);
            if (col == -1) { // what should be done?
                throw new IllegalArgumentException("symbol not found in the alphabet: " + c);
            }
            state = states[state][col];
            util.MyDebug.println("state: " + state);
            if (isAccepting(state)) {
                m.add(index + 1 - plen); // match begins at index + 1 - plen
            }
            index += 1;
        }
        return m;
    }

    boolean isAccepting(int state) {
        return state == states.length - 1;
    }
    int dfaIndex(char ch) {
        return alphabet.indexOf(ch) + 1; // this is short, but expensive!
    }
    int genomicIndex(int ch) {
        // return a -1 when the ch does not represent a genomic character
        switch (ch) {
            case 'A': return 1;
            case 'C': return 2;
            case 'G': return 3;
            case 'T': return 4;
            default: return -1; //deciding not to incur throw-catch overhead, but instead design by contract
        }
    }
    public List<Integer> matches(IntStream chars) {
        List<Integer> m = new ArrayList<>();
/*
        int plen = pattern.length();
        int state = 0; // the start state
        int index = 0;
        chars.forEachOrdered(c -> {
            state = states[state][genomicIndex(c)];
            if (isAccepting(state)) {
                m.add(index + 1 - plen); // match begins at index + 1 - plen
            }
            index += 1;
        });
*/
        return m;
    }

    public boolean match(String text) {
        return !this.matches(text).isEmpty();
    }

    public static DFAMatcher createGenomicPatternMatcher(String pattern) {
        return new DFAMatcher(pattern, GENOMIC_ALPHABET);
    }

    static int longestProperPrefixSuffixLength(String candidate) {
        //returns the longest length of prefix of candidate that is also its suffix
        int longest = 0;
        int len = candidate.length();
        for (int i = len - 1; i >= 1; i--) {
            String iPrefix = candidate.substring(0, i);
            String iSuffix = candidate.substring(len - i, len);
//            util.MyDebug.println(iPrefix + ", " + iSuffix);
            if (iPrefix.equals(iSuffix)) {
                longest = iPrefix.length();
                assert longest == iSuffix.length();
                break;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        DFAMatcher dfam = createGenomicPatternMatcher("GATT");
        MyDebug.println(dfam.matches("TTGATTGATT"));
    }
}
