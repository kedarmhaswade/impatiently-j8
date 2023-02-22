package practice.sg;

/**
 * <p>
 * Represents the data of an edge of a graph <em> with respect to a vertex</em> which is <i>not</i> represented in
 * the instances of this class. Callers construct instances of this class when they know a node and want to store
 * information with respect to that node.
 * </p>
 * <p>
 * The main ingredients are the other end and the edge-weight. Can be
 * extended to store additional information.
 * </p>
 */
public final class EdgeData {

    private final int other;
    private final double weight;

    public EdgeData(int other, double weight) {
        if (other <= 0) {
            throw new IllegalArgumentException("other end must be positive, not: " + other);
        }
        this.other = other;
        this.weight = weight;
    }

    public int getOther() {
        return this.other;
    }

    public double getWeight() {
        return weight;
    }


    @Override
    public boolean equals(Object other) {
        if (other instanceof EdgeData) {
            EdgeData that = (EdgeData) other;
            return this.other == that.other && this.weight == that.weight;
        }
        return false;
    }

    @Override
    public String toString() {
        return "<" + other + ">: " + weight;
    }
}
