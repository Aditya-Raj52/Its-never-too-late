class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffix = new int[n];
        int mn = Integer.MAX_VALUE;
        if(n <= 1) return 0;
        for(int i = n-1 ; i >= 0; i--){
            mn = Math.min(mn, nums[i]);
            suffix[i] = mn;
        }
        int mx = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            mx = Math.max(mx, nums[i]);
            if((mx - suffix[i]) <= k){
                return i;
            }
        }
        return -1;
    }
}