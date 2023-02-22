package practice.sg;

import practice.sg.Graphs.EdgeType;

import java.util.*;
import java.util.stream.Collectors;

import static practice.sg.Graphs.EdgeType.UNDIRECTED;

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
 *
 * @see EdgeSpec
 */
public final class EdgeListGraph {
    /**
     * This is the main data structure of this graph regardless of whether it's a directed or undirected graph.
     * We retain the entire {@linkplain EdgeData} instance so that we have access to edge weights.
     */
    private final Map<Integer, Set<EdgeData>> adjList;
    private final int order; // this is a "derived" characteristic of the graph
    private final EdgeType eType;

    public EdgeListGraph(List<EdgeSpec> specList, EdgeType eType) {
        this.eType = eType;
        this.adjList = new HashMap<>();
        int eCount = 0;
        for (EdgeSpec spec : specList) {
            eCount += 1;
            adjList.putIfAbsent(spec.getFrom(), new HashSet<>());
            adjList.get(spec.getFrom()).add(spec.getEdgeData());
            if (eType == UNDIRECTED) { // add the edge for both vertices
                adjList.putIfAbsent(spec.getTo(), new HashSet<>());
                adjList.get(spec.getTo()).add(spec.reverse().getEdgeData());
            }
        }
        this.order = eCount;
    }

    public static EdgeListGraph of(List<EdgeSpec> edges, EdgeType eType) {
        return new EdgeListGraph(edges, eType);
    }

    public Set<Integer> neighbor(int src) {
        return this.adjList.get(src).stream().map(EdgeData::getOther).collect(Collectors.toSet());
    }

    public int size() {
        return adjList.size(); // one mapping per vertex
    }

    public int order() {
        return this.order;
    }

    private EdgeType getType() {
        return this.eType;
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

    After speaking with Apoorv, it became clear that perhaps we should design the API around a natural way of doing
    things. First off, a call to bfs should only concern the component of the graph of which the starting node is a part.
    The issue of bfs not identifying all the components can be left to the clients.
     */

    public boolean bfs(int s, int[] d, int[] p) {
        // returns true if the number of nodes reached == size of the graph, false otherwise
        // historical note: While implementing this method I un-generified this class. I gave up the notion that
        // the graph vertices should be of some generic type that is "Comparable" in favor of plain integers.
        // The need was felt for the distance and parent arrays which would be generic arrays and the simplicity of
        // these 1-D arrays representing 2-D data structure like a tree (e.g. the bfs tree) comes from the fact
        // that nodes are integers.
        int sz = this.size();
        if (d.length <= sz) {
            throw new IllegalArgumentException("the distance array size must be > the number of vertices: " + sz);
        }
        if (p.length <= sz) {
            throw new IllegalArgumentException("the bfs tree array size must be > the number of vertices: " + sz);
        }
        Deque<Integer> q = new ArrayDeque<>(sz);
        Set<Integer> v = new HashSet<>(sz);
        q.add(s); // start at the very beginning
        v.add(s);
        d[s] = 0;
        p[s] = -1; // starting node has no parent
        int numVisited = 1;
        while (!q.isEmpty()) {
            int curr = q.poll();
            Set<Integer> cNebs = this.neighbor(curr);
            if (cNebs == null) {
                continue;
            }
            for (Integer other : cNebs) {
                if (v.contains(other)) {
                    continue;
                }
                q.add(other);
                v.add(other);
                numVisited++;
                d[other] = d[curr] + 1;
                p[other] = curr;
            }
        }
        return numVisited == sz;
    }
}
