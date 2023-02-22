package practice.sg;

/**
 * Simply represents a (textual) specification of an edge.
 *
 * <p>Here is the textual representation of an example undirected graph: </p>
 * <pre>
 *      {"AGraph", {{1, 4}, {1, 5}, {1, 6}, {2, 5}, {2, 6}, {3, 6}}}
 *  </pre>
 * <p>Here is the textual representation of an example directed graph: </p>
 * <pre>
 *     {"AGraph", {{1, 4}, {1, 5}, {1, 6}, {2, 5}, {2, 6}, {3, 6}, {4, 1}, {5, 1}, {5, 2}, {6, 1}, {6, 2}, {6, 3}}}
 *  </pre>
 */
public final class EdgeSpec {
    private final int from;
    private final int to;
    private final double weight;

    public EdgeSpec(int from, int to) {
        this(from, to, 0);
    }

    public EdgeSpec(int from, int to, double weight) {
        if (from <= 0 || to <= 0) {
            throw new IllegalArgumentException("values must be positive");
        }
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    public static EdgeSpec of(int from, int to, double w) {
        return new EdgeSpec(from, to, w);
    }

    public static EdgeSpec of(int from, int to) {
        return new EdgeSpec(from, to, 0);
    }

    public EdgeSpec reverse() {
        return of(this.getTo(), this.getFrom(), this.getWeight());
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public double getWeight() {
        return weight;
    }

    public EdgeData getEdgeData() {
        // keyed on the "from"
        return new EdgeData(this.to, this.weight);
    }

    @Override
    public String toString() {
        return from + "-" + to + (weight == 0 ? "" : "-" + weight);
    }
}
