class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int m = 0;
        int[] vals = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) vals[m++] = sorted[i];
        }
        long[] cntT = new long[m + 1];
        long[] sumT = new long[m + 1];

        int LOG = Integer.highestOneBit(m);
        long[] cost = new long[n - x + 1];
        long total = 0;

        for (int i = 0; i < n; i++) {
            int idx = Arrays.binarySearch(vals, 0, m, nums[i]) + 1;
            update(cntT, sumT, idx, 1, nums[i], m);
            total += nums[i];
            if (i >= x) {
                int old = Arrays.binarySearch(vals, 0, m, nums[i - x]) + 1;
                update(cntT, sumT, old, -1, -nums[i - x], m);
                total -= nums[i - x];
            }
            if (i >= x - 1) {
                long rem = (x + 1) / 2;
                int pos = 0;
                for (int pw = LOG; pw > 0; pw >>= 1) {
                    if (pos + pw <= m && cntT[pos + pw] < rem) {
                        pos += pw;
                        rem -= cntT[pos];
                    }
                }
                int medIdx = pos + 1;
                long med = vals[medIdx - 1];
                long cntLE = 0, sumLE = 0;
                for (int p = medIdx; p > 0; p -= p & -p) {
                    cntLE += cntT[p];
                    sumLE += sumT[p];
                }
                cost[i - x + 1] = med * cntLE - sumLE
                                + (total - sumLE) - med * (x - cntLE);
            }
        }

        final long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1];
        for (int j = 1; j <= k; j++) {
            long[] cur = new long[n + 1];
            Arrays.fill(cur, INF);
            for (int i = 1; i <= n; i++) {
                cur[i] = cur[i - 1];
                if (i >= x && prev[i - x] < INF) {
                    cur[i] = Math.min(cur[i], prev[i - x] + cost[i - x]);
                }
            }
            prev = cur;
        }
        return prev[n];
    }

    private void update(long[] cntT, long[] sumT, int i, int dc, long ds, int m) {
        for (; i <= m; i += i & -i) {
            cntT[i] += dc;
            sumT[i] += ds;
        }
    }
}