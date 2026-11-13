import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] b = new int[n];
        for (int t = 0; t < n; t++) b[t] = nums[n - 1 - t];
        long[][] dq = new long[n][2];
        int head = 0, tail = 0; 

        long cost = 0, ans = 0;
        int i = 0;

        for (int j = 0; j < n; j++) {
            long cnt = 1;
            while (tail > head && dq[tail - 1][0] <= b[j]) {
                cost += (b[j] - dq[tail - 1][0]) * dq[tail - 1][1];
                cnt += dq[tail - 1][1];
                tail--;
            }
            dq[tail][0] = b[j];
            dq[tail][1] = cnt;
            tail++;

            while (cost > k) {
                cost -= dq[head][0] - b[i];
                if (--dq[head][1] == 0) head++;
                i++;
            }
            ans += j - i + 1;
        }
        return ans;
    }
}