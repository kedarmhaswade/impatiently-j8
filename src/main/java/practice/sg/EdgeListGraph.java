package practice.sg;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * Graphs are complex. Determining a graph representation format that satisfies their enormity is quite challenging.
 * In order to start somewhere, however, I am going to adhere to only one kind of representation. This representation
 * is loosely based on Mathematica's Graph Data format:
 * </p>
 * <p>An edge is simply represented as a 3-tuple
 * <code>&lt;f, t, [w]></code> where <code>f, t</code> are some identifiers denoting the nodes and the optional
 * <code>w</code> is a real number representing an edge-weight.
 * </p>
 * <p>
 * An EdgeListGraph can be directed or undirected.
 * </p>
 * <p>Here is the textual representation of an example graph: </p>
 * <pre>
 *     {"AGraph", {{1, 4}, {1, 5}, {1, 6}, {2, 5}, {2, 6}, {3, 6}, {4, 1}, {5, 1}, {5, 2}, {6, 1}, {6, 2}, {6, 3}}}
 * </pre>
 */
public class EdgeListGraph<T extends Comparable<T>> {
    private final Set<T> vertices;
    private final List<? super Edge<T>> edges;
    private final Map<T, List<Edge<T>>> neighbors; // trading some space for efficiency

    public EdgeListGraph(List<Edge<T>> edges) {
        List<? super Edge<T>> tmp = new ArrayList<>(edges.size());
        Collections.copy(tmp, edges);
        this.edges = Collections.unmodifiableList(tmp);
        vertices = new HashSet<>();
        edges.forEach(e -> {
            vertices.add(e.from);
            vertices.add(e.to);
        });
        this.neighbors = edges.stream().collect(Collectors.groupingBy(Edge::getFrom));
    }

    public List<Edge<T>> adj(T src) {
        return this.neighbors.get(src);
    }

    public long size() {
        return vertices.size();
    }

    public long order() {
        return edges.size();
    }


    /*
    Implementation note: Deciding a signature of the method for breadth first search is challenging.
    Joyner/Nguyen/Phillips book says that bfs should return two things:
    1) the distance of each node from the starting node, and
    2) the bfs tree as an array (can also be called the parents array).

    But a given graph may have more than one connected component. In case it does, we need to either accept
    or assume a starting node when we find that there are some unreachable nodes after a run of bfs. However,
    the caller does not know a priori how many connected components a graph has. Finding connected components
    is one of the applications of bfs. Therefore, a generally useful API for bfs is rather elusive.
     */
}
