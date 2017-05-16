package practice.ej;

import static java.util.Objects.requireNonNull;

public final class OrderlyName implements Comparable<OrderlyName> {
    private final String last, first;

    OrderlyName(String first, String last) {
        requireNonNull(first);
        requireNonNull(last);
        this.last = last;
        this.first = first;
    }
    @Override
    public int compareTo(OrderlyName that) {
        int c1 = this.last.compareToIgnoreCase(that.last);
        return c1 == 0 ? this.first.compareTo(that.first) : c1;
    }

    @Override
    public String toString() {
        // Demo: comment this method out for toString power
        return "[" + first + " " + last + "]"; // todo optimize as a good citizen
    }
}