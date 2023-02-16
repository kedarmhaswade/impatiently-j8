package practice.sg;

public final class Edge<T extends Comparable<T>> {
    final T from; // package-private
    final T to; // package-private
    final double w; // package-private

    public Edge(T from, T to) {
        this(from, to, 0);
    }

    public Edge(T from, T to, double w) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("null values");
        }
        this.from = from;
        this.to = to;
        this.w = w;
    }

    public T getFrom() {
        return from;
    }

    public T getTo() {
        return to;
    }

    public double getWeight() {
        return w;
    }
}
