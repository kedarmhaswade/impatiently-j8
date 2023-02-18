package practice.sg;

import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public final class UndirectedEdge {

    private final Set<Integer> ends;
    private final double weight;

    public UndirectedEdge(Set<Integer> ends, double weight) {
        if (ends.size() != 2) {
            throw new IllegalArgumentException("an edge is between two nodes, not: " + ends.size());
        }
        this.ends = unmodifiableSet(ends);
        this.weight = weight;
    }

    public UndirectedEdge(Set<Integer> ends) {
        this(ends, 0);
    }

    public Set<Integer> ends() {
        return this.ends;
    }

    public double getWeight() {
        return weight;
    }

    public int getOther(int end) {
        Integer[] between = ends().toArray(new Integer[0]); // I hate Java
        assert between.length == 2 : "there must be two ends, not: " + between.length;
        if (ends().contains(end)) {
            return between[0] == end ? between[1] : between[0];
        }
        throw new AssertionError("illegal end: " + end + " for the edge: " + this.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof UndirectedEdge) {
            UndirectedEdge that = (UndirectedEdge) other;
            return this.ends().equals(that.ends()) && this.getWeight() == that.getWeight();
        }
        return false;
    }

    @Override
    public String toString() {
        return ends.toString() + (this.weight == 0 ? "" : "-" + this.weight);
    }
}
