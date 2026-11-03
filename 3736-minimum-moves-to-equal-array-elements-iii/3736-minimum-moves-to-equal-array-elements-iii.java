class Solution {
    public int minMoves(int[] nums) {
        int maxVal = nums[0];
        int sum = 0;
        
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
            sum += num;
        }
        System.gc();
        return (maxVal * nums.length) - sum;
    }
}