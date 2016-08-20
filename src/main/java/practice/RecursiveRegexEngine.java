package practice;

/**
 * <p>
 *     Implements a subset of (obviously) the more complete regular expression matching capabilities. Following
 *     specialities are handled:
 *     <ul>
 *     <li>anchors ^ and $</li>
 *     <li>.</li>
 *     <li>s* -- matches a string that contains 0 or more symbols s</li>
 *     <li>.?</li>
 *     </ul>
 * </p>
 * Created by kmhaswade on 8/17/16.
 */
public class RecursiveRegexEngine {

    static boolean matches(String regex, String s) {
        if (regex.charAt(0) == '^') { // enforce the ^ anchor
            return matchesHere(regex.substring(1), s);
        }
        int slen = s.length();
        for (int i = 0; i < slen; i++) {
            if (matchesHere(regex, s.substring(i)))
                return true;
        }
        return false;
    }

    /**
     * Handles various cases recursively; starts matching the given regex with the given string s
     * from the 0th character of s.
     * @param regex the regular expression; assumed to be valid
     * @param s the string to match regex with
     * @return true if regex matches s, false otherwise
     */
    private static boolean matchesHere(String regex, String s) {
        if (regex.isEmpty()) {
            return true; // empty regex matches anything
        }
        if ("$".equals(regex)) {
            return s.isEmpty(); // attempt $ anchor
        }
        int rlen = regex.length();
        int slen = s.length();
        if (rlen >= 2 && regex.charAt(1) == '*') {
            for (int i = 0; i < slen && (regex.charAt(0) == '.' || regex.charAt(0) == s.charAt(i)); i++) {
                if (matchesHere(regex.substring(2), s.substring(i + 1)))
                    return true;
            }
            return matchesHere(regex.substring(2), s); // attempt with zero repetitions of character before *
        }
        // attempt single character match
        return !s.isEmpty()
                && (regex.charAt(0) == '.' || regex.charAt(0) == s.charAt(0))
                && matchesHere(regex.substring(1), s.substring(1))  ;
    }
}
