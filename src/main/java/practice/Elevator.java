package practice;

import java.util.*;

/** <p> Jones/Pevzner -- An Introduction to Bioinformatics Algorithms Problem 6.9.</p>
 * A building with an elevator has n levels. The elevator has m buttons that can move it by a given number
 *  of levels (+ indicates up, - indicates down). For example, if the buttons are +3, -2, then the first button
 *  takes you 3 levels up and the second one takes you two levels down. In each step you press any button once.
 * <p>
 *     Given a start level, src, how can you reach another level, target,
 *     in minimum number of steps? Will you be able to tell if you will ever reach the target level?
 * </p>
 * Created by kmhaswade on 6/3/16.
 */
public final class Elevator {
    private final int n;
    private final int[] m;
    public Elevator(int n, int ... m) {
        this.n = n;
        this.m = m;
        //# levels = m.length
    }
    private static final class Vertex<T> {
        final T value;
        final Vertex parent;
        Vertex(T value) {
            this.value = value;
            this.parent = null;
        }
        Vertex(T value, Vertex parent) {
            this.value = value;
            this.parent = parent;
        }
    }
    public int reach(int src, int target) {
        // validate src, target
        Deque<Vertex<Integer>> parents = new ArrayDeque<>(this.n);
        BitSet everVisited = new BitSet(this.n);
        parents.add(new Vertex<>(src));
        everVisited.set(src);
        while (!parents.isEmpty()) {
//            parents.stream().forEach(v-> System.out.println(v.value));
            Vertex<Integer> current = parents.remove();
            if (current.value == target) {
                return printTravel(current);
            }
            addUnvisitedDestinations(current, parents, everVisited);
        }
        System.out.println("No way to reach level: " + target + ", from level: " + src);
        return -1;
    }

    private int printTravel(Vertex<Integer> current) {
        System.out.println("Great! Reached Level: " + current.value);
        int steps = 0;
        while (current != null && current.parent != null) {
            System.out.println("Came From Level: " + current.parent.value);
            current = current.parent;
            steps += 1;
        }
        return steps; //
    }

    private void addUnvisitedDestinations(Vertex<Integer> current, Deque<Vertex<Integer>> to, BitSet everVisited) {
        // note: mutates "to" and everVisited
        int len = this.m.length;
        for (int i = 0; i < len; i++) {
            int d = current.value + (m[i]);
            Vertex<Integer> candidate = new Vertex<Integer>(d, current);
            if (d >= 1 && d <= n && !everVisited.get(d)) {
                to.add(candidate);
                everVisited.set(d);
            }
        }
    }

    public static void main(String[] args) {
        Elevator e = new Elevator(50, -2, 4, 9);
        System.out.println(e.reach(14, 41));
    }
}
