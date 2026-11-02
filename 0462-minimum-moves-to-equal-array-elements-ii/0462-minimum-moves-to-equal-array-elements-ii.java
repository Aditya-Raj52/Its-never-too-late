class Solution {
    public int minMoves2(int[] nums) {
        int sum = 0;
        int min = nums[0];
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        for(int i = 0; i < nums.length; i++){
            int add = Math.abs(nums[i] - median);
            sum += add;
        }
        return sum;
    }
}