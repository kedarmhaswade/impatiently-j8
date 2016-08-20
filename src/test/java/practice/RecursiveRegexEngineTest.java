package practice;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static practice.RecursiveRegexEngine.matches;

/**
 * Created by kmhaswade on 8/18/16.
 */
public class RecursiveRegexEngineTest {

    @Test
    public void testMatches() throws Exception {
        String r = "a*b";
        String s = "aab";
        assertTrue(matches(r, s));
        r = "^a*b$";
        s = "aab";
        assertTrue(matches(r, s));
        r = "a*b*c";
        s = "c";
        assertTrue(matches(r, s));
        r = ".*bc";
        s = "Abc";
        assertTrue(matches(r, s));
    }
    @Test
    public void testDoesNotMatch() {
        String r = "a*$";
        String s = "aab";
        assertFalse(matches(r, s));
        r = ".ab.";
        s = "aaacbaca";
        assertFalse(matches(r, s));
    }
}