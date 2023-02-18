package practice.sg;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class EdgeListGraphTest {

    /**
     * Tests {@linkplain EdgeListGraph} for <code>AGraph</code> that has the following edges:
     * <pre>
     * {1, 4}, {1, 5}, {1, 6}, {2, 5}, {2, 6}, {3, 6}, {4, 1}, {5, 1}, {5, 2}, {6, 1}, {6, 2}, {6, 3}
     * </pre>
     */
    @Test
    public void aGraphTest() {
        EdgeListGraph g = Graphs.AGraphDirected;
        assertEquals("num nodes = 6", 6L, g.size()); // has 6 nodes
        assertEquals("num edges = 12", 12L, g.order()); // has 12 edges
        List<Integer> n5 = g.neighbor(5);
        assertEquals("num neighbors of 5 is 2", 2, n5.size());
        assertTrue("2 is an out-neighbor of 5", n5.contains(2));
        assertTrue("1 is an out-neighbor of 5", n5.contains(1));
    }

    @Test
    public void butterflyBfs() {
        EdgeListGraph g = Graphs.ButterflyGraphUndirected;
        assertEquals("butterfly graph has 5 nodes", 5, g.size());
        assertEquals("butterfly graph has 6 edges", 6, g.order());
        int[] d = new int[6];
        d[0] = -1;
        int[] p = new int[6];
        p[0] = -1;
        boolean coversAllVertices = g.bfs(4, d, p);
        assertTrue("bfs spans all the vertices", coversAllVertices);
        int[] ed = new int[]{-1, 1, 2, 2, 0, 1}; // expected distances
        int[] ep = new int[]{-1, 4, 1, 1, -1, 4}; // expected bfs tree
        assertArrayEquals("distance array is: " + Arrays.toString(ed), ed, d);
        assertArrayEquals("distance array is: " + Arrays.toString(ep), ep, p);
    }
}