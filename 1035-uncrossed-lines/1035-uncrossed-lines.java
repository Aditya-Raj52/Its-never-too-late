class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int idx1 = nums1.length;
        int idx2 = nums2.length;
    
        int dp[][] = new int[idx1+1][idx2+1];
        for (int i = 1; i <= idx1; i++){
            for( int j = 1; j <= idx2; j++){
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                else{
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i][j-1]);
                }
            }
        }
        return dp[idx1][idx2];
    }
}