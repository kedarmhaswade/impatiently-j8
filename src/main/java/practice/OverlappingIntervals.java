package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * <p> Given two sorted lists of non-overlapping, closed integer intervals (i.e. [a, b] where a &lt; b and a anb b are
 * ints) find all the intersecting or overlapping intervals.</p> <p> Example:</p>
 * <pre>
 *  Input:
 *  xs = [[1, 3], [5, 11], [14, 17], [20, 22]]
 *  ys = [[2, 5], [6, 10], [15, 18], [20, 22]]
 *
 *  Expected output:
 *  [[2, 3], [6, 10], [15, 17], [20, 22]]
 * </pre>
 */
public class OverlappingIntervals {

    public static void main(String[] args) {
        int[][] xs = new int[][] {{1, 3}, {5, 11}, {14, 17}, {20, 22}};
        int[][] ys = new int[][] {{2, 5}, {6, 10}, {15, 18}, {20, 22}};
        List<Interval> left = intervals(xs);
        List<Interval> right = intervals(ys);
        check(left, right);
        left = intervals(new int[][] {{1, 10}});
        right = intervals(new int[][] {{2, 3}, {4, 5}, {6, 7}, {9, 11}});
        check(left, right);
        left = intervals(new int[][] {{2, 3}, {4, 5}, {6, 7}, {9, 11}});
        right = intervals(new int[][] {{1, 10}});
        check(left, right);
        left = intervals(new int[][] {{1, 2}});
        right = intervals(new int[][] {{4, 5}});
        check(left, right);
    }

    private static void check(List<Interval> left, List<Interval> right) {
        int i = 0;
        int j = 0;
        List<Interval> overlapping = new ArrayList<>(left.size());
        while (i < left.size() && j < right.size()) {
            Pair p = left.get(i).compareTo(right.get(j));
//            System.out.println("got: " + p);
            if (p.interval != Interval.NONE) {
                boolean added = overlapping.add(p.interval);
                assert added;
            }
            if (p.order == -1) {
                i += 1;
            } else if (p.order == 1) {
                j += 1;
            } else {
                i += 1;
                j += 1;
            }
        }
        System.out.println(overlapping);
    }

    private static List<Interval> intervals(int[][] xs) {
        return Arrays
            .stream(xs).map(a -> new Interval(a[0], a[1]))
            .collect(toList());
    }

    static final class Pair {
        final Interval interval;
        final int order;

        Pair(Interval interval, int order) {
            this.interval = interval;
            this.order = order;
        }

        @Override
        public String toString() {
            return "{" + interval.toString() + ", " + order + "}";
        }
    }

    static final class Interval {
        static final Interval NONE = new Interval(0, 0); // lo and hi do not matter, we do identity comparison
        final int lo;
        final int hi;

        Interval(int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
        }

        Pair compareTo(Interval that) {
//            System.out.println("comparing: " + this + " to: " + that);
            if (this.lo > that.hi) {
                return new Pair(NONE, 1);
            } else if (this.hi < that.lo) {
                return new Pair(NONE, -1);
            } else if (this.lo < that.lo && this.hi > that.hi) {
                return new Pair(that, 1);
            } else if (this.lo < that.lo && this.hi < that.hi) {
                return new Pair(new Interval(that.lo, this.hi), 1);
            } else if (this.lo > that.lo && this.hi > that.hi) {
                return new Pair(new Interval(this.lo, that.hi), -1);
            } else if (this.lo > that.lo && this.hi < that.hi) {
                return new Pair(this, -1);
            } else {
                return new Pair(this, 0);
            }
        }

        @Override
        public String toString() {
            return "[" + this.lo + ", " + this.hi + "]";
        }
    }
}
