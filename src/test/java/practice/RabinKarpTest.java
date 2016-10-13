package practice;

import org.junit.Test;

import static org.junit.Assert.*;
import static practice.DFAMatcher.GENOMIC_ALPHABET;

/**
 * Created by kedar on 2/10/16.
 */
public class RabinKarpTest {
    @Test
    public void index() throws Exception {
        String text = "abcdefghi";
        String string = "def";
        assertEquals(3L, RabinKarp.index(text, string));
        string = "ghi";
        assertEquals(6L, RabinKarp.index(text, string));
    }
    @Test
    public void testGenomic() {
        String text = Utils.randomString(1_000_000, "MNOP", 1234);
        char[] t = text.toCharArray();
        String string = Utils.randomString(1000, "ATGC", 1234);
        char[] s = string.toCharArray();
        System.arraycopy(s, 0, t, t.length - s.length, s.length);
        text = new String(t);
        assertEquals(999000L, RabinKarp.index(text, string));
    }

}