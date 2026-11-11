class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long totalCost = 0;
        long common = Math.min(need1 , need2);
        totalCost += common * Math.min((long) costBoth , (long) cost1 + cost2);

        if(need1>need2){
            long rem1 = need1 - need2;
            totalCost += rem1 * Math.min((long) costBoth, (long)cost1);
        }
        else if(need2 > need1){
            long rem2 = need2 - need1;
            totalCost += rem2 * Math.min((long) costBoth, (long)cost2);
        }
        return totalCost;
    }
}