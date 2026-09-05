class Solution {
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int idx1 = nums1.length;
        int idx2 = nums2.length;
        int[] prev = new int[idx2 + 1];
        int[] curr = new int[idx2 + 1]; 
       
        for (int i = 1; i <= idx1; i++){
            for( int j = 1; j <= idx2; j++){
                if(nums1[i - 1] == nums2[j - 1]){
                    curr[j] = 1 + prev[j - 1];
                }
                else{
                    curr[j] = Math.max(prev[j],curr[j-1]);
                }
            }
            prev = curr.clone();
        }
        return prev[idx2];
    }
}