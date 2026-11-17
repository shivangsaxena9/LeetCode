import java.util.*;

class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        final int INF = 1_000_000;
        char[] c = s.toCharArray();
        List<Integer>[] divs = new List[n + 1];
        for (int i = 0; i <= n; i++) divs[i] = new ArrayList<>();
        for (int d = 1; d <= n; d++)
            for (int len = 2 * d; len <= n; len += d)
                divs[len].add(d);

        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int len = j - i + 1;
                int best = INF;
                for (int d : divs[len]) {
                    int changes = 0;
                    for (int r = 0; r < d; r++) {
                        int a = i + r, b = j - d + 1 + r;
                        while (a < b) {
                            if (c[a] != c[b]) changes++;
                            a += d;
                            b -= d;
                        }
                    }
                    best = Math.min(best, changes);
                }
                cost[i][j] = best;
            }
        }

        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, INF);
        dp[0][0] = 0;

        for (int t = 1; t <= k; t++) {
            for (int j = 2 * t; j <= n - 2 * (k - t); j++) {
                for (int i = 2 * (t - 1); i <= j - 2; i++) {
                    if (dp[t - 1][i] >= INF) continue;
                    dp[t][j] = Math.min(dp[t][j], dp[t - 1][i] + cost[i][j - 1]);
                }
            }
        }
        return dp[k][n];
    }
}