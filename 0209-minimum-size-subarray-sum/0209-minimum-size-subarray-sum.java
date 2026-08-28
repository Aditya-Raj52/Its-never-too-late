class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int currSum = 0;
        int l = 0;
        int min = Integer.MAX_VALUE;
        
        
        for (int r = 0; r < nums.length; r++){
            currSum += nums[r];

            while(currSum >= target){
                min = Math.min(min,r - l + 1);
                currSum -= nums[l];
                l++;
            }         
        }
        return min == Integer.MAX_VALUE ? 0 : min;

    }
}