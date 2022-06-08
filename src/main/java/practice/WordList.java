package practice;

/**
 * Simple program that enumerates wordlists of various lengths given a certain characters.
 */
public class WordList {
    private static final char[] alphabet = new char[]{
        'अ',
        'आ',
        'इ',
        'ई',
        'उ',
        'ऊ',
        'ए',
        'ऐ',
        'ओ',
        'औ',
        'क',
        'ख',
        'ग',
        'घ',
        'च',
        'छ',
        'ज',
        'झ',
        'ट',
        'ठ',
        'ड',
        'ढ',
        'ण',
        'त',
        'थ',
        'द',
        'ध',
        'न',
        'प',
        'फ',
        'ब',
        'भ',
        'म',
        'य',
        'र',
        'ल',
        'व',
        'श',
        'स',
        'ष',
        'ह',
//         'क्ष',  // not a character!
//         'ज्ञ',  // not a character!
    };

    static void printValidGuesses(int len, String w) {
        if (len == 0) {
            System.out.println("'" + w + "'" + ",");
        } else {
            for (char c : alphabet) {
                if (w.length() >= 1 && c == w.charAt(w.length() - 1)) {
                    continue;
                }
                printValidGuesses(len - 1, w + c);
            }
        }
    }

    public static void main(String[] args) {
        printValidGuesses(3, "");
    }
}
