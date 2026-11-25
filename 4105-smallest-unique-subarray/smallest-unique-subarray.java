import java.util.*;

class Solution {
    private static final long BASE1 = 100_003L;
    private static final long MOD1  = 1_000_000_007L;
    private static final long BASE2 = 1_000_003L;
    private static final long MOD2  = 1_000_000_009L;

    private long[] pref1, pref2, pow1, pow2;
    private int n;

    public int smallestUniqueSubarray(int[] nums) {
        this.n = nums.length;
        pref1 = new long[n + 1];
        pref2 = new long[n + 1];
        pow1  = new long[n + 1];
        pow2  = new long[n + 1];

        pow1[0] = 1L;
        pow2[0] = 1L;

        for (int i = 0; i < n; i++) {
            long val = nums[i];
            pow1[i + 1] = (pow1[i] * BASE1) % MOD1;
            pow2[i + 1] = (pow2[i] * BASE2) % MOD2;

            pref1[i + 1] = (pref1[i] * BASE1 + val) % MOD1;
            pref2[i + 1] = (pref2[i] * BASE2 + val) % MOD2;
        }

        int lo = 1, hi = n, ans = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (hasUniqueLength(mid)) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    private boolean hasUniqueLength(int len) {
        Map<Long, Integer> freq = new HashMap<>((n - len + 1) * 2);
        for (int l = 0; l + len <= n; l++) {
            int r = l + len;
            long h1 = (pref1[r] - (pref1[l] * pow1[len]) % MOD1 + MOD1) % MOD1;
            long h2 = (pref2[r] - (pref2[l] * pow2[len]) % MOD2 + MOD2) % MOD2;
            long combined = (h1 << 32) | (h2 & 0xffffffffL);
            freq.merge(combined, 1, Integer::sum);
        }

        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }
        return false;
    }
}