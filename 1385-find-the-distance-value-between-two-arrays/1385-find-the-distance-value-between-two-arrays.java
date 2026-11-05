import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        int count = 0;
        int j = 0;
        
        for (int i = 0; i < arr1.length; i++) {
            // Skip all elements in arr2 that are too small (< arr1[i] - d)
            while (j < arr2.length && arr2[j] < arr1[i] - d) {
                j++;
            }
            if (j < arr2.length && arr2[j] <= arr1[i] + d) {
            } 
            else {
                count++;
            }
        }
        
        return count;
    }
}
