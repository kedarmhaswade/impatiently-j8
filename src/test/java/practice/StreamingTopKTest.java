package practice;

import org.junit.Test;
import practice.StreamingTopK.CountNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kedar on 11/5/16.
 */
public class StreamingTopKTest {

    @Test
    public void testIncrementally() {
        StreamingTopK<String> dh = new StreamingTopK<>(1<<10);
        // 6 are added with a:3, b:2, c:1, so the top two should be a and b
        dh.read("a");
        dh.read("b");
        dh.read("a");
        dh.read("b");
        dh.read("a");
        dh.read("c");
        final List<CountNode<String>> topk = new ArrayList<>(2);
        dh.query(2, cn -> topk.add(cn));
        assertEquals(3L, topk.get(0).count);
        assertEquals("a", topk.get(0).key);
        assertEquals("b", topk.get(1).key);
        // now let's make b the leader and c the runner-up
        dh.read("b");
        dh.read("b");
        dh.read("b");
        // now b is read 5times, a 3 times and c once
        dh.read("c");
        dh.read("c");
        dh.read("c");
        dh.read("d");
        //b : 5, c: 4, a: 3, d: 1
        topk.clear();
        dh.query(3, cn -> topk.add(cn));
        assertEquals(5L, topk.get(0).count);
        assertEquals("b", topk.get(0).key);
        assertEquals(4L, topk.get(1).count);
        assertEquals("c", topk.get(1).key);
        assertEquals(3L, topk.get(2).count);
        assertEquals("a", topk.get(2).key);
    }

}