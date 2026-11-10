class Solution {
    public int climbStairs(int n, int[] costs) {
        long[] dp = new long[n+1];
        java.util.Arrays.fill(dp,Long.MAX_VALUE);
        dp[0] = 0;

        for(long i=0;i<=n;i++){
            int curr = (int) i;
            if(dp[curr]==Long.MAX_VALUE) continue;
            for(int jump = 1;jump<=3;jump++){
                int nextStep = curr+jump;
                if(nextStep <= n ){
                    long cost = dp[curr]+costs[nextStep - 1]+(long)(nextStep - curr)*(nextStep - curr);
                    dp[nextStep] = Math.min(dp[nextStep],cost);
                }
            }
        }
        return(int)dp[n];
    }
}