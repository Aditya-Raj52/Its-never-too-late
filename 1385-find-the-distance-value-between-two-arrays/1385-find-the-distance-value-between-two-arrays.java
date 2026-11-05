class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        
        for (int n : arr1) {
            boolean isValid = true;
            for (int e : arr2) {
                if (Math.abs(e - n) <= d) {
                    isValid = false;
                    break; // Found an element within distance d, no need to check the rest
                }
            }
            if (isValid) {
                count++;
            }
        }
        
        return count;
    }
}