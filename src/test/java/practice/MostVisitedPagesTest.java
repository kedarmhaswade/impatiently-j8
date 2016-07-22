package practice;

import org.junit.Test;

import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Created by kmhaswade on 7/19/16.
 */
public class MostVisitedPagesTest {

    @Test
    public void supersede() {
        MostVisitedPages m = new MostVisitedPages();
        String pg1 = "a";
        for (int i = 1; i <= 5; i++) {
            m.read(pg1);
        }
        List<Entry<String, Long>> list = m.find(1);
        assertEquals(pg1, list.get(0).getKey());
        assertEquals(5, (long) list.get(0).getValue());
        String pg2 = "b";
        for (int i = 1; i <= 6; i++) {
            m.read(pg2);
        }
        // now pg2 should supersede
        list = m.find(2);
        assertEquals(pg2, list.get(0).getKey());
        assertEquals(6, (long) list.get(0).getValue());
        assertEquals(pg1, list.get(1).getKey());
        assertEquals(5, (long) list.get(1).getValue());
    }
    @Test
    public void tryBookEx() {
        // test in the book
        MostVisitedPages m = new MostVisitedPages();
        Stream.of("g", "a", "t", "t", "a", "a", "a", "g", "t", "c", "t", "a", "t").forEach(p -> m.read(p));
        List<Entry<String, Long>> topk = m.find(4);
        // a is first
        assertEquals("a", topk.get(0).getKey());
        assertEquals(5, (long) topk.get(0).getValue());
        // t second
        assertEquals("t", topk.get(1).getKey());
        assertEquals(5, (long) topk.get(1).getValue());
        // g third
        assertEquals("g", topk.get(2).getKey());
        assertEquals(2, (long) topk.get(2).getValue());
        // c fourth
        assertEquals("c", topk.get(3).getKey());
        assertEquals(1, (long) topk.get(3).getValue());

    }
}