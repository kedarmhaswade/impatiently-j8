package practice;

/**
 * Created by kmhaswade on 5/9/16.
 */
public class LCS {
    public static int[][] distances(String v, String w) {
        int n = v.length();
        int m = w.length();
        int[][] d = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            d[i][0] = 0;
        for (int i = 0; i <= n; i++)
            d[0][i] = 0;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                int max = Integer.MIN_VALUE;
                if (v.charAt(i) == w.charAt(j))
                    max = d[i - 1][j - 1] + 1; // from diagonal only if there is a match
                if (d[i - 1][j] > max)
                    max = d[i - 1][j];
                if (d[i][j - 1] > max)
                    max = d[i][j - 1];
                d[i][j] = max;
            }
        }
        return d;
    }
    public static String lcs(String v, String w, int[][] d) {
        return null;
    }
}
