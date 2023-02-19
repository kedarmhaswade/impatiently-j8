package practice.sg;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static practice.sg.Graphs.BullGraphUndirected;
import static practice.sg.Graphs.ButterflyGraphUndirected;

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
        EdgeListGraph g = ButterflyGraphUndirected;
        int es = 5;
        assertEquals("butterfly graph #nodes: " + es, es, g.size());
        int eo = 6;
        assertEquals("butterfly graph has #edges: " + eo, eo, g.order());
        int[] d = new int[es + 1];
        d[0] = -1; // node w number 0 does not exist
        int[] p = new int[es + 1];
        p[0] = -1; // node w number does not exist
        int start = 4;
        d[start] = 0;
        p[start] = -1;
        boolean coversAllVertices = g.bfs(start, d, p);
        assertTrue("bfs spans all the vertices", coversAllVertices);
        int[] ed = new int[]{-1, 1, 2, 2, 0, 1}; // expected distances
        int[] ep = new int[]{-1, 4, 1, 1, -1, 4}; // expected bfs tree, starting node has no parent
        assertArrayEquals("distance array is: " + Arrays.toString(ed), ed, d);
        assertArrayEquals("bfs tree array is: " + Arrays.toString(ep), ep, p);
    }

    @Test
    public void bullGraphBfs() {
        EdgeListGraph g = BullGraphUndirected;
        int es = 9;
        assertEquals("butterfly graph #nodes: " + es, es, g.size());
        int eo = 8;
        assertEquals("butterfly graph has #edges: " + eo, eo, g.order());
        int[] d = new int[es + 1];
        d[0] = -1; // node w number 0 does not exist
        int[] p = new int[es + 1];
        p[0] = -1; // node w number 0 has no parent
        int start = 5;
        d[start] = 0;
        p[start] = -1;
        boolean coversAllVertices = g.bfs(start, d, p);
        assertFalse("bfs first run does not cover all the vertices", coversAllVertices);
        // only the head is identified after first bfs because the starting node is in that component
        int[] ed = new int[]{-1, 1, 2, 1, 1, 0, 0, 0, 0, 0}; // expected distances
        int[] ep = new int[]{-1, 5, 4, 5, 5, -1, 0, 0, 0, 0}; // expected bfs tree, starting node has no parent
        assertArrayEquals("distance array after first is: " + Arrays.toString(ed), ed, d);
        assertArrayEquals("bfs tree array is: " + Arrays.toString(ep), ep, p);
        start = 9;
        coversAllVertices = g.bfs(start, d, p);
        assertFalse("bfs second run also does not cover all the vertices", coversAllVertices);
        // only the head is identified after first bfs because the starting node is in that component
        ed = new int[]{-1, 1, 2, 1, 1, 0, 2, 3, 1, 0}; // expected distances
        ep = new int[]{-1, 5, 4, 5, 5, -1, 8, 6, 9, -1}; // expected bfs tree, starting node has no parent
    }
}