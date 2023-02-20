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
    private final double w;

    public EdgeSpec(int from, int to) {
        this(from, to, 0);
    }

    public EdgeSpec(int from, int to, double w) {
        if (from <= 0 || to <= 0) {
            throw new IllegalArgumentException("values must be positive");
        }
        this.from = from;
        this.to = to;
        this.w = w;
    }

    public static EdgeSpec of(int from, int to, double w) {
        return new EdgeSpec(from, to, w);
    }

    public static EdgeSpec of(int from, int to) {
        return new EdgeSpec(from, to, 0);
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public double getWeight() {
        return w;
    }

    @Override
    public String toString() {
        return from + "-" + to + (w == 0 ? "" : "-" + w);
    }
}
