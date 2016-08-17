package practice;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static practice.MatchedParens.longest;

/**
 * Created by kmhaswade on 8/14/16.
 */
public class MatchedParensTest {

    @Test
    public void testLongest() throws Exception {
        char[] c = "()()()()".toCharArray();
        assertEquals(8L, longest(c));
        c = "(((())))()".toCharArray();
        assertEquals(10L, longest(c));
        c = "((())".toCharArray();
        assertEquals(4L, longest(c));
    }
}