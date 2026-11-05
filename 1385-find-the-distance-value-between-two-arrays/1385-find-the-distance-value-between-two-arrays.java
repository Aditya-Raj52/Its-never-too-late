import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Sort arr2 to enable binary search
        Arrays.sort(arr2);
        int count = 0;
        
        for (int val : arr1) {
            int idx = Arrays.binarySearch(arr2, val);
            
            // If exact match is found, distance is 0, which is <= d (invalid)
            if (idx >= 0) {
                continue;
            }
            
            // Convert negative return value to insertion point
            int insertionPoint = -idx - 1;
            boolean isValid = true;
            
            // Check the closest element greater than val
            if (insertionPoint < arr2.length && arr2[insertionPoint] - val <= d) {
                isValid = false;
            }
            // Check the closest element smaller than val
            else if (insertionPoint > 0 && val - arr2[insertionPoint - 1] <= d) {
                isValid = false;
            }
            
            if (isValid) {
                count++;
            }
        }
        
        return count;
    }
}