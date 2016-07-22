package practice;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by kmhaswade on 7/20/16.
 */
public class MostVisitedPagesInWindowTest {

    @Test
    public void tryBookExWithK1() {
        MostVisitedPagesInWindow m = new MostVisitedPagesInWindow(4);
        m.read("g", 1);
        m.read("a", 2);
        m.read("t", 3);
        m.read("t", 6);
        m.read("a", 7);
        // (g, 1), (a, 2) are expired at this point, and t is most frequently accessed -- twice
        List<Map.Entry<String, Long>> list = m.find(1); // just one entry
        assertEquals(1, list.size());
        assertEquals("t", list.get(0).getKey());
        assertEquals(2, (long) list.get(0).getValue());
    }
    @Test
    public void tryBookExWithK2() {
        MostVisitedPagesInWindow m = new MostVisitedPagesInWindow(4);
        m.read("g", 1);
        m.read("a", 2);
        m.read("t", 3);
        m.read("t", 6);
        m.read("a", 7);
        // (g, 1), (a, 2) are expired at this point, and t is most frequently accessed -- twice
        // and a is the second most frequently accessed page with count 1
        List<Map.Entry<String, Long>> list = m.find(2);
        assertEquals(2, list.size());
        assertEquals("t", list.get(0).getKey());
        assertEquals(2, (long) list.get(0).getValue());
        assertEquals("a", list.get(1).getKey());
        assertEquals(1, (long) list.get(1).getValue());
    }

}