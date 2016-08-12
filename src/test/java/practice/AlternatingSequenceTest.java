package practice;

import org.junit.Test;

import java.util.List;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;
import static practice.AlternatingSequence.arrange;

/**
 * Created by kmhaswade on 8/11/16.
 */
public class AlternatingSequenceTest {

    @Test
    public void testArrange() throws Exception {
        List<Integer> a = asList(2, 1, 4, 3);
        arrange(a);
        assertAlternation(a);
        a = asList(-1, 2, 2, 0, 4, 1, 20, 3, -3);
        arrange(a);
        assertAlternation(a);
    }
    static <T extends Comparable<T>> void assertAlternation(List<T> list) {
        int n = list.size();
        if (n <= 1)
            throw new RuntimeException();
        for (int i = 1; i < n; i++) {
            if (i % 2 != 0)
                assertTrue(list.get(i-1).compareTo(list.get(i)) <= 0);
            else
                assertTrue(list.get(i-1).compareTo(list.get(i)) >= 0);
        }
    }
}