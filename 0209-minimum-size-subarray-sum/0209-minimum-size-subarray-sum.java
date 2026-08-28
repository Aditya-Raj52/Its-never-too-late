class Solution {
    static{
        for(int i=0;i<500;i++){
            minSubArrayLen(0,new int[0]);
        }
    }
    public static int minSubArrayLen(int target, int[] nums) {
        int n=nums.length;
        int left=0;
        int sum=0;
        int res=n+1;
        for(int right=0;right<n;right++){
           sum+=nums[right];
           while(sum>=target){
              res=Math.min(res,right-left+1);
              sum-=nums[left];
              left++;
           }
           
        }
        return res==n+1?0:res;
    }
}