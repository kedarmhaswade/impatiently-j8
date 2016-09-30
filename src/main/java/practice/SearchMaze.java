package practice;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *     Determines if a given maze (a 2-D array) with white squares (available) and black squares
 *     (unavailable) has a path from a certain starting square to certain ending square.
 * </p>
 * <p>
 *     Classic application of DFS. Recursive backtracking is what we should do here.
 * </p>
 * Created by kmhaswade on 9/28/16.
 */
public class SearchMaze {
    final static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) { //x, y are non-negative
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point that = (Point)o;
                return this.x == that.x && this.y == that.y;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return (11 * x) + (17 * y);
        }
    }

    public static boolean exists(int x1, int y1, int x2, int y2, boolean[][] maze) {
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        Set<Point> visited = new HashSet<>();
        return dfs(p1, p2, visited, maze);
    }
    static boolean dfs(Point p1, Point p2, Set<Point> visited, boolean[][] maze) {
        if (p2.equals(p1)) {
            return true;
        }
        Set<Point> reachables = getReachablePoints(p1, maze);
        if (visited.contains(p1) || reachables.size() == 0) {
            return false; // avoid cycles
        }
        visited.add(p1); // visit the first square
        for (Point p : reachables) {
            if (dfs(p, p2, visited, maze))
                return true;
        }
        return false;
    }

    private static Set<Point> getReachablePoints(Point p1, boolean[][] maze) {
        Set<Point> r = new HashSet<>(4);
        // cache these guys!
        Point left = null, right = null, top = null, bottom = null;
        if (p1.x > 0 && maze[p1.x - 1][p1.y])
            left = new Point(p1.x - 1, p1.y);
        if (p1.x < maze.length - 1 && maze[p1.x + 1][p1.y])
            right = new Point(p1.x + 1, p1.y);
        if (p1.y > 0 && maze[p1.x][p1.y - 1])
            bottom = new Point(p1.x, p1.y - 1);
        if (p1.y < maze[0].length - 1 && maze[p1.x][p1.y + 1])
            top = new Point(p1.x, p1.y + 1);
        if (left != null)
            r.add(left);
        if (right != null)
            r.add(right);
        if (top != null)
            r.add(top);
        if (bottom != null)
            r.add(bottom);
        return r;
    }
}
