package practice;

import java.util.*;

/** <p>
 *  While reading Ore's book on Graphs, I read: "[on a chessboard]
 *  It is not difficult to see that the knight can reach any square from any original position,
 *  so the game graph is connected."
 * </p>
 * <p>
 *     This may be true with a regular 8x8 chessboard, but clearly, it is not true with a 2x2 or a 3x3
 *     chessboard.
 * </p>
 * In essence then, we should be able to find the "knight move getComponents" of a given nxn chessboard. If the
 * graph representing n^2 vertices linked by "knight move edges", has <b>one connected component</b>, then all the
 * squares can be reached from any square by "knight moves".
 * <p>
 *     This is an opportunity to implement the union-find algorithm.
 * </p>
 * Created by kmhaswade on 6/17/16.
 */
public class KnightMoveComponent {

    private static Map<String, Set<String>> knightPositions = new HashMap<>();
    static Set<String> getKnightPositions(String position, int n) {
        Set<String> positions = knightPositions.get(position);
        if (positions == null) {
            char a = 'a', h = (char)(a + n - 1);
            int hr = n, lr = 1;
            positions = new HashSet<>();
            char file = position.charAt(0);
            int rank = position.charAt(1) - '0';
            if ((file - a + 1) <= n &&  rank <= n) {
                if (file + 1 <= h && rank + 2 <= hr)
                    positions.add("" + (char) (file + 1) + (char) (rank + 2 + '0'));
                if (file + 1 <= h && rank - 2 >= lr)
                    positions.add("" + (char) (file + 1) + (char) (rank - 2 + '0'));
                if (file - 1 >= a && rank + 2 <= hr)
                    positions.add("" + (char) (file - 1) + (char) (rank + 2 + '0'));
                if (file - 1 >= a && rank - 2 >= lr)
                    positions.add("" + (char) (file - 1) + (char) (rank - 2 + '0'));
                if (file + 2 <= h && rank + 1 <= hr)
                    positions.add("" + (char) (file + 2) + (char) (rank + 1 + '0'));
                if (file + 2 <= h && rank - 1 >= lr)
                    positions.add("" + (char) (file + 2) + (char) (rank - 1 + '0'));
                if (file - 2 >= a && rank + 1 <= hr)
                    positions.add("" + (char) (file - 2) + (char) (rank + 1 + '0'));
                if (file - 2 >= a && rank - 1 >= lr)
                    positions.add("" + (char) (file - 2) + (char) (rank - 1 + '0'));
                knightPositions.put(position, positions);
            }
        }
//        System.out.println(positions);
        return positions;
    }


    private static final class Vertex {
        final String position;
        final Set<Vertex> edges = new LinkedHashSet<>();
        Vertex component = null;
        Vertex(String position) {
            this.position = position;
        }
        void addEdge(Vertex v) {
            boolean added = this.edges.add(v);
            assert added : "edge to vertex: " + v + " already exists";
        }
        @Override
        public String toString() {
            return this.position + " (" + (component == null ? null : component.position) + ")";
        }
        @Override
        public boolean equals(Object o) {
            return o != null && o instanceof Vertex && ((Vertex)o).position.equals(this.position);
            // no regards to edges!
        }
        @Override
        public int hashCode() {
            return position.hashCode();
            // no regards to edges
        }
    }
    public static void main(String[] args) {
        List<Vertex> knightGraph = createKnightGraph(8);
        Set<Vertex> components = getComponents(knightGraph);
        System.out.println("number of getComponents: " + components.size());
        System.out.println(components);
    }

    private static Set<Vertex> getComponents(List<Vertex> knightGraph) {
        // This implements the Hopcroft-Tarjan algorithm using bfs
        // https://en.wikipedia.org/wiki/Connected_component_(graph_theory)#Algorithms
        Set<Vertex> components = new LinkedHashSet<>();
        for (Vertex v : knightGraph) {
            if (v.component == null) {
                v.component = v;
                bftComponents(v);
                components.add(v);
            }
        }
        return components;
    }

    /**
     * Performs the breadth first traversal from the given vertex and given queue. Marks each
     * vertex thus reaches with a component that is v.
     * @param v the component identifier vertex
     */
    private static void bftComponents(Vertex v) {
        Deque<Vertex> q = new ArrayDeque<>();
        Set<Vertex> visited = new HashSet<>();
        q.add(v);
        visited.add(v);
        while (!q.isEmpty()) {
            Vertex tmp = q.remove();
            Set<Vertex> edges = tmp.edges;
            edges.stream().forEach(u -> {
                if (!visited.contains(u)) {
                    u.component = v;
                    q.add(u);
                    visited.add(u);
                }
            });
        }
    }

    private static List<Vertex> createKnightGraph(int n) {
        List<Vertex> g = new ArrayList<>();
        Map<String, Vertex> cache = new HashMap<>();
        char firstFile = 'a', lastFile = (char) (firstFile + n - 1);
        for (char file = firstFile; file <= lastFile; file = (char) (file + 1))
            for (int rank = 1; rank <= n; rank ++) {
                String pos = "" + file + (char) ('0' + rank);
                Vertex v = cache.containsKey(pos) ? cache.get(pos) : null;
                if (v == null) {
                    v = new Vertex(pos);
                    cache.put(pos, v);
                }
                Set<String> positions = getKnightPositions(pos, n);
                for (String p : positions) {
                    Vertex w = cache.containsKey(p) ? cache.get(p) : null;
                    if (w == null) {
                        w = new Vertex(p);
                        cache.put(p, w);
                    }
                    v.addEdge(w);
                }
                g.add(v);
            }
        return g;
    }
}
