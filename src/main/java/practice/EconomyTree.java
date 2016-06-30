package practice;

import java.util.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p>
 *     Ore-Wilson's book describes an algorithm to generate a tree from a graph that has the minimum cost. Thus,
 *     if we are given a network of roads to be constructed and we know the cost of building each road, how can we
 *     construct a tree that has minimum total cost?
 * </p>
 * <p>
 *     This is also called the <code>Minimum Spanning Tree </code> of a given graph.
 * </p>
 * <p>
 *     The algorithm implemented here is a simple greedy algorithm that breaks the ties arbitrarily. It returns all
 *     the <i> economy trees </i> of a given graph.
 * </p>
 * Created by kmhaswade on 6/24/16.
 */
public class EconomyTree {

    private static final class Vertex<T> {
        final T key;
        Vertex<T> component;
        Vertex(T key) {
            this.key = key;
            this.component = this;
        }
        @Override
        public String toString() {
            String c = this.component == null ? "null" : this.component.key.toString();
            return this.key.toString() + "(" + c + ")";
        }
        @Override
        public boolean equals(Object o) {
            return o instanceof Vertex && this.key.equals(((Vertex) o).key);
        }
        @Override
        public int hashCode() {
            return this.key.hashCode();
        }
    }
    private static final class Edge<T> {
        final double weight;
        final Vertex<T> v1;
        final Vertex<T> v2;
        Edge(Vertex<T> v1, Vertex<T> v2, double weight) {
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
        final static Comparator<Edge> WEIGHT_COMPARATOR = (o1, o2) -> Double.compare(o1.weight, o2.weight);
        @Override
        public String toString() {
            return v1 + " " + v2;
        }
    }
    private static final class Graph<T extends Comparable<T>> {
        final int nVertices;
        final List<Edge<T>> edges;
        final Map<T, Set<Vertex<T>>> disjointSet; // the disjoint disjointSet
        Graph(int nVertices, List<Edge<T>> edges) {
            this.nVertices = nVertices;
            this.edges = edges;
            this.disjointSet = new HashMap<>();
            makeSet();
        }

        private void makeSet() {
            for (Edge<T> edge : edges) {
                T k1 = edge.v1.key;
                T k2 = edge.v2.key;
                if (! disjointSet.containsKey(k1))
                    disjointSet.put(k1, new HashSet<>());
                if (! disjointSet.containsKey(k2))
                    disjointSet.put(k2, new HashSet<>());
                disjointSet.get(k1).add(edge.v1);
                disjointSet.get(k2).add(edge.v2);
            }
            assert disjointSet.size() == this.nVertices;
        }

        public T find (Vertex<T> v) {
            return v.component.key;
        }

        public void merge(Vertex<T> v1, Vertex<T> v2) {
            Vertex<T> winner, loser;
            int cmp = v1.key.compareTo(v2.key);
            assert cmp != 0;
            if (cmp < 0) {
                winner = v1;
                loser = v2;
            } else {
                winner = v2;
                loser = v1;
            }
            winner.component = winner;
            loser.component = winner;
            disjointSet.get(loser.key).forEach(v -> v.component = winner);
            disjointSet.get(winner.key) // guaranteed to be non-null
                       .addAll(disjointSet.get(loser.key));
            disjointSet.put(loser.key, disjointSet.get(winner.key));
        }
    }
    static Graph<Integer> createGraph(Stream<String> lines) {
        final HashMap<Integer, Vertex<Integer>> vertexCache = new HashMap<>();
        final List<Edge<Integer>> edges = new ArrayList<>();
        lines.forEach(line -> {
            String[] parts = line.split("\\s");
            if (parts.length != 4) {
                // each edge is: 1 1 3 0.5
                System.out.println("ignoring line: " + line);
                System.out.println("valid format: <edge-id> <vertex-1> <vertex-2> <weight>");
            }
            Integer k1 = Integer.valueOf(parts[1]);
            Integer k2 = Integer.valueOf(parts[2]);
            double w = Double.valueOf(parts[3]);
            Vertex v1 = get(k1, vertexCache), v2 = get(k2, vertexCache);
            edges.add(new Edge<>(v1, v2, w));
        });
        return new Graph<>(vertexCache.size(), edges);
    }
    static List<Edge<Integer>> getEconomyTree(Graph<Integer> g) {
        // A tree is a list of n-1 edges from the given n-vertex graph g such that there are no cycles.
        // We consider the weights while creating the economy or minimum spanning tree that has minimum total weight.
        // the given graph will be mutated
        Collections.sort(g.edges, Edge.WEIGHT_COMPARATOR);
        //  this is a greedy algorithm
        List<Edge<Integer>> tree = new ArrayList<>();
        g.makeSet();
        int i = 0;
        double totalWt = 0.0;
        while (tree.size() < (g.nVertices - 1)) { // num of edges in the tree must be g.nVertices - 1
            Edge<Integer> tmp = g.edges.get(i);
            Vertex<Integer> v1 = tmp.v1;
            Vertex<Integer> v2 = tmp.v2;
            if (v1.component != v2.component) {
                g.merge(v1, v2);
                tree.add(tmp);
                totalWt += tmp.weight;
                System.out.println("added to tree, the edge: " + tmp + ", total weight: " + totalWt);
            } else {
                System.out.println("this edge: " + tmp + " will form a cycle, skipping it ...");
            }
            i += 1;
        }
        return tree;
    }

    private static Vertex get(Integer k, HashMap<Integer, Vertex<Integer>> vertexCache) {
        Vertex v;
        if (vertexCache.containsKey(k)) {
            v = vertexCache.get(k);
        } else {
            v = new Vertex(k);
            vertexCache.put(k, v);
        }
        return v;
    }

    public static void main(String[] args) {
        Stream<String> lines = Stream.of("1 1 2 3\n",
                                         "2 1 3 4\n",
                                         "3 1 4 3\n",
                                         "4 2 3 5\n",
                                         "5 2 4 2\n",
                                         "6 3 4 5\n"
                );
        Graph<Integer> g = createGraph(lines);
        List<Edge<Integer>> tree = getEconomyTree(g);
        System.out.println("tree: " + tree);
        lines = Stream.of("1 1 2 1\n",
                          "2 2 3 3\n",
                          "3 3 5 5\n",
                          "4 5 6 7\n",
                          "5 1 6 4\n",
                          "6 1 3 4\n",
                          "7 3 4 2\n",
                          "8 4 5 4\n",
                          "9 4 6 3\n",
                          "10 1 4 3\n"
        );
        g = createGraph(lines);
        tree = getEconomyTree(g);
        System.out.println("tree: " + tree);
    }
}
