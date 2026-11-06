class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        int m = queries.length;
        Integer[] order = new Integer[m];
        for (int i = 0; i < m; i++) order[i] = i;
        Arrays.sort(order, (a, b) -> Integer.compare(queries[a], queries[b]));

        int[] cnt = new int[n + 1];
        int[] res = new int[m];
        int active = 0;
        int l = 0, r = 0;
        int L = logs.length;

        for (int idx : order) {
            int q = queries[idx];
            while (r < L && logs[r][1] <= q) {
                if (cnt[logs[r][0]]++ == 0) active++;
                r++;
            }
            while (l < r && logs[l][1] < q - x) {
                if (--cnt[logs[l][0]] == 0) active--;
                l++;
            }
            res[idx] = n - active;
        }
        return res;
    }
}