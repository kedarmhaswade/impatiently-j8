package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * Given a rectangular grid with m rows and n columns containing only ones and zeros,
 * can you find the number of islands? An island is defined as a set of cells that all
 * have a value 1 and that are connected either horizontally or vertically.
 * For example, the grid:
 * [
 * [1, 0, 0, 0, 1, 0, 0],
 * [1, 1, 1, 1, 1, 0, 0],
 * [0, 0, 0, 0, 0, 1, 1],
 * [1, 1, 0, 0, 0, 1, 1]
 * ]
 * has 3 islands.
 * </pre>
 */
public class Islands {
    public static void main(String[] args) {
        int n = traverse(new int[][] {
            {1, 0, 0, 0, 1, 0, 0},
            {1, 1, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 1, 1}
        });
        System.out.println("number of islands (dfs): " + n);
        n = traverse(new int[][] {
            {1, 1, 1, 0, 1, 1, 0}
        });
        System.out.println("number of islands (dfs): " + n);
        n = traverse(new int[][] {
            {1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1}
        });
        System.out.println("number of islands (dfs): " + n);
        n = traverse(new int[][] {
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1},
        });
        System.out.println("number of islands (dfs): " + n);
    }
    private static int traverse(int[][] graph) {
        int count = 0;
        boolean[][] visited = new boolean[graph.length][graph[0].length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] == 1 && !visited[i][j]) {
                    dfs(graph, visited, i, j);
                    count += 1; // done with that connected component
                }
            }
        }
        return count;
    }
    private static void dfs(int[][] graph, boolean[][] visited, int i, int j) {
        if (graph[i][j] == 1 && !visited[i][j]) {
            visited[i][j] = true;
            int[][] edges = next(graph, i, j);
            for (int[] edge : edges) {
                dfs(graph, visited, edge[0], edge[1]);
            }
        }
    }

    private static int[][] next(int[][] graph, int i, int j) {
        List<int[]> n = new ArrayList<>(2);
        int previ = i - 1;
        int prevj = j - 1;
        int nextj = j + 1;
        int nexti = i + 1;
        if (nextj < graph[i].length && graph[i][nextj] == 1) { // east
            n.add(new int[] {i, nextj});
        }
        if (nexti < graph.length && graph[nexti][j] == 1) { // south
            n.add(new int[] {nexti, j});
        }
        if (previ >= 0 && graph[previ][j] == 1) { // north
            n.add(new int[] {previ, j});
        }
        if (prevj >= 0 && graph[i][prevj] == 1) { // west
            n.add(new int[] {i, prevj});
        }
        return n.toArray(new int[0][]);
    }
}
