package practice;

import org.junit.Test;
import util.MyDebug;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static practice.DFAMatcher.createGenomicPatternMatcher;
import static practice.DFAMatcher.longestProperPrefixSuffixLength;

/**
 * Created by kmhaswade on 6/1/16.
 */
public class DFAMatcherTest {

    @Test
    public void testLongestLength() throws Exception {
        String s = "ABCAB";
        assertEquals(2, longestProperPrefixSuffixLength(s));
        s = "ABC";
        assertEquals(0, longestProperPrefixSuffixLength(s));
        s = "AAA";
        assertEquals(2, longestProperPrefixSuffixLength(s));
        s = "ABAB";
        assertEquals(2, longestProperPrefixSuffixLength(s));
    }

    @Test
    public void testMatches() throws Exception {
        String p = "GGG";
        DFAMatcher dfam = createGenomicPatternMatcher(p);
        List<Integer> m = dfam.matches("GGGG");
        MyDebug.println(m);
//        assertThat(m, IsIterableContainingInOrder.contains(0, 4));

//        String pattern = Utils.randomString(1000, GENOMIC_ALPHABET, 1234);
//        System.out.println(pattern);
//        dfam = new DFAMatcher(pattern, GENOMIC_ALPHABET);
//        String text = Utils.randomString(1_000_000, GENOMIC_ALPHABET, 4244);
//        StringBuilder sb = new StringBuilder(text).append(pattern);
//        text = sb.toString();
//        assertTrue(dfam.match(text));
    }
}