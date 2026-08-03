class Solution {
    public int[] twoSum(int[] nums, int target) {
        int l=nums.length;
        for(int j=1;j<l;j++){
            for(int i=j;i<l;i++){
                if(nums[i]+nums[i-j]==target){
                    return new int[]{i-j,i};
                }
            }
        }
    return new int[]{};
    }
        
}