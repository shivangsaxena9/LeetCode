class Solution {
    public int numOfSubarrays(int[] arr) {
        int odd = 0, even = 1;
        int parity = 0;
        long ans = 0;

        for (int num : arr) {
            parity ^= (num & 1);
            if (parity == 1) {
                ans += even;
                odd++;
            } else {
                ans += odd;
                even++;
            }
        }

        return (int) (ans % 1_000_000_007);
    }
}