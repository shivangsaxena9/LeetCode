import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length, M = n;
        long minPrice = Long.MAX_VALUE;
        int[] cnt = new int[M + 1];
        for (int[] it : items) { cnt[it[0]]++; minPrice = Math.min(minPrice, it[1]); }

        boolean[] comp = new boolean[M + 1];
        for (int p = 2; p <= M; p++) {
            if (comp[p]) continue;
            for (long j = (long) p * p; j <= M; j += p) comp[(int) j] = true;
            for (int i = M / p; i >= 1; i--) cnt[i] += cnt[i * p];
        }

        long[] pr = new long[n], gn = new long[n];
        int sz = 0;
        for (int[] it : items) {
            long g = cnt[it[0]] - 1;
            if (g > 0 && it[1] < 2 * minPrice) { pr[sz] = it[1]; gn[sz++] = g; }
        }
        pr = Arrays.copyOf(pr, sz); gn = Arrays.copyOf(gn, sz);

        long rem = budget, total = 0;
        Random rnd = new Random();
        while (pr.length > 0) {
            long pivot = pr[rnd.nextInt(pr.length)];
            int a = 0, c = 0;
            for (long x : pr) { if (x < pivot) a++; else if (x > pivot) c++; }
            long[] lp = new long[a], lg = new long[a], gp = new long[c], gg = new long[c];
            long costL = 0, gainL = 0, gainE = 0;
            int ia = 0, ic = 0;
            for (int i = 0; i < pr.length; i++) {
                if (pr[i] < pivot) {
                    lp[ia] = pr[i]; lg[ia++] = gn[i];
                    costL = Math.min(costL + pr[i] * gn[i], rem + 1);
                    gainL += gn[i];
                } else if (pr[i] == pivot) gainE += gn[i];
                else { gp[ic] = pr[i]; gg[ic++] = gn[i]; }
            }
            if (costL > rem) { pr = lp; gn = lg; continue; }
            rem -= costL; total += 2 * gainL;
            long k = Math.min(gainE, rem / pivot);
            total += 2 * k; rem -= k * pivot;
            if (k < gainE) break;
            pr = gp; gn = gg;
        }
        total += rem / minPrice;
        return (int) total;
    }
}