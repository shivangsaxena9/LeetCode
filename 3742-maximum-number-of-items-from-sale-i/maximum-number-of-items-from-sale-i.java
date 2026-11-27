class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freeGain = new int[n];
        for (int i = 0; i < n; i++) {
            int fi = items[i][0];
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % fi == 0) {
                    count++;
                }
            }
            freeGain[i] = count;
        }

        int[] dp = new int[budget + 1];
        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            if (price > budget) {
                continue;
            }
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1 + freeGain[i]);
            }
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }
        int ans = 0;
        for (int val : dp) {
            ans = Math.max(ans, val);
        }
        return ans;
    }
}