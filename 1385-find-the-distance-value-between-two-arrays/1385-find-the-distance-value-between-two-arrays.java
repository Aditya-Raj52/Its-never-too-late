class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        int n2 = arr2.length;
        
        for (int i = 0; i < arr1.length; i++) {
            boolean valid = true;
            int val = arr1[i];
            for (int j = 0; j < n2; j++) {
                if (Math.abs(val - arr2[j]) <= d) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                count++;
            }
        }
        return count;
    }
}