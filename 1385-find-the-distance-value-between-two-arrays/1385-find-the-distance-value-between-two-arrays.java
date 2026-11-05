import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Sort arr2 so we can perform binary search
        Arrays.sort(arr2);
        int count = 0;
        
        for (int val : arr1) {
            // Check if there is any element in arr2 within [val - d, val + d]
            if (isValid(arr2, val, d)) {
                count++;
            }
        }
        
        return count;
    }
    
    private boolean isValid(int[] arr, int target, int d) {
        int left = 0;
        int right = arr.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // If we find ANY element whose distance is <= d, this target is INVALID
            if (Math.abs(arr[mid] - target) <= d) {
                return false;
            }
            
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        // No element in arr2 was within distance d
        return true; 
    }
}
