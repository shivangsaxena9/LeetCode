class Solution {
    public int longestMonotonicSubarray(int[] nums) {
       int max1=0,max2=0,i=0,j=1;
       if(nums.length==1)return 1;
       while(j<nums.length){
        if(nums[j-1]<nums[j]){
            j++;
        }else{
            i=j;
            j++;
        }
        max1=Math.max(j-i,max1);
       }
       j=1;
       i=0;
       while(j<nums.length){
        if(nums[j-1]>nums[j]){
            j++;
        }else{
            i=j;
            j++;
        }
        max2=Math.max(j-i,max2);
       }
       return Math.max(max1,max2); 
    }
}