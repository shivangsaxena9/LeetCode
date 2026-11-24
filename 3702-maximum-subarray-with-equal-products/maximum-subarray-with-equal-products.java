class Solution {
    public int maxLength(int[] nums) {
        int[] masks = {0, 0, 1, 2, 1, 4, 3, 8, 1, 2, 5};
        int maxLen = 2;
        int left = 0, windowMask = 0;
        
        for (int right = 0; right < nums.length; right++) {
            int currMask = masks[nums[right]];
            
            while ((windowMask & currMask) != 0) {
                windowMask ^= masks[nums[left]];
                left++;
            }
            
            windowMask |= currMask;
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}