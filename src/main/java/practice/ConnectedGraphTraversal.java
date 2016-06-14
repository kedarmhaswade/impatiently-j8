package practice;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *     Given a connected graph (results are undefined otherwise), one should be able to traverse it such that
 *     <ul>
 *         <li> You come back to the starting vertex.</li>
 *         <li> You take each edge e (a0a1) exactly twice, once from a0 to a1 and once from a1 to a0.</li>
 *     </ul>
 *     The starting vertex can be chosen at random.
 * </p>
 * This class implements an algorithm for such a traversal of any graph.
 * Note that there is no restriction on the graph (other than connectedness).
 * <p> The connectedness of a graph can be verified using the union-find algorithm.</p>
 * Created by kmhaswade on 6/12/16.
 */
public class ConnectedGraphTraversal {

    static Set<Vertex> createGraph(Stream<String> lines) {
        // the lines are of the form: 1 2,3,4 i.e. vertex comma separated neighbors
        Set<Vertex> vertices = new HashSet<>();
        lines.forEach(line -> {
            String[] parts = line.split("\\s");
            int vid = Integer.parseInt(parts[0]);
            String[] ns = parts[1].split(","); // could be more accommodating
            List<Integer> neighbors = Arrays.stream(ns).mapToInt(Integer::parseInt).collect(ArrayList<Integer>::new,
                    ArrayList::add,
                    ArrayList::addAll);
            Vertex v = new Vertex(vid, neighbors);
            vertices.add(v);
//            System.out.println(v);
        });
        return vertices;
    }

    static void traverse(Set<Vertex> graph) {
        int sum = graph.stream().mapToInt(v -> v.neighbors.size()).sum();
        Vertex from = getRandomStartingVertex(graph);
        System.out.println("start: " + from + ", remaining: " + graph.size());
        Vertex to = null;
        int i = 0;
        while (i < sum) {
//            to = graph.stream().filter(v -> v.id == from.leave()).findFirst().get();
            Integer tmp = from.leave();
            for (Vertex v : graph) {
                if (v.id == tmp) {
                    to = v;
                    break;
                }
            }
            System.out.println(from.id + " -> " + to.id);
            to.arrive(from);
            from = to;
            i += 1;
        }
    }

    private static Vertex getRandomStartingVertex(Set<Vertex> graph) {
        Random r = new Random();
        int n = r.nextInt(graph.size());
        Iterator<Vertex> iter = graph.iterator();
        int i = 0;
        while (iter.hasNext()) {
            Vertex d = iter.next();
            if (i == n) {
//                iter.remove();
                return d;
            }
            i += 1;
        }
        throw new AssertionError("An item should have been found");
    }

    public static void main(String[] args) {
        StringReader sr = new StringReader("1 2,3,5\n2 1,4,5\n3 1,4\n4 2,3\n5 1,2\n");
//        StringReader sr = new StringReader("1 2,3,4\n2 1,3,4\n3 1,2,4\n4 1,2,3\n");
        Set<Vertex> g = createGraph(new BufferedReader(sr).lines());
        traverse(g);
    }
    private static final class Vertex {
        final int id;
        private final LinkedHashSet<Integer> arrivedFrom = new LinkedHashSet<>();
        private final LinkedHashSet<Integer> leftTo = new LinkedHashSet<>();
        private final HashSet<Integer> neighbors = new HashSet<>();
        private Vertex firstArrivingFrom = null;

        Vertex(int id, List<Integer> neighbors) {
            this.id = id;
            this.neighbors.addAll(neighbors.stream().collect(Collectors.toList()));
        }
        public void arrive(Vertex from) {
            if (arrivedFrom.isEmpty()) {
                firstArrivingFrom = from;
            }
            arrivedFrom.add(from.id);
        }
        public Integer leave() {
            // returns the ID of the vertex to which we go
            if (leftTo.size() == neighbors.size() - 1) { // last choice
                assert firstArrivingFrom != null;
                int id = firstArrivingFrom.id;
                leftTo.add(id);
                return id;
            }
            for (Integer neighbor : neighbors) {
                if (!leftTo.contains(neighbor)) {
                    if (firstArrivingFrom != null && firstArrivingFrom.id == neighbor)
                        continue; // we skip the special vertex from which the first arrival occurred here
                    leftTo.add(neighbor);
                    return neighbor;
                }
            }
            throw new AssertionError("all done!");
        }
        @Override
        public String toString() {
            return "vertex: " + this.id + ", neighbors: " + this.neighbors;
        }
    }
}
