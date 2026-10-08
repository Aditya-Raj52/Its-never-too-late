class Solution {
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        int prev = nums[0];
        int prev2 = 0;
        int curr = 0;
        for(int i = 1; i < nums.length - 1; i++){
            int pick = nums[i];
            if(i > 1) pick += prev2;
            int np = prev;
            curr = Math.max(pick,np);
            prev2 = prev;
            prev = curr;
        }

        int back = nums[1];
        int back2 = 0;
        int current = 0;
        for(int i = 2; i < nums.length; i ++){
            int pick = nums[i] + back2;
            int np = back;
            current = Math.max(pick, np);
            back2 = back;
            back = current;

        }
        return Math.max(prev,back);
    }
}