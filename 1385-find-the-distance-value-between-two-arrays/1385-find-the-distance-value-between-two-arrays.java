import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);
        int count = 0;
        int n2 = arr2.length;
        
        for (int a : arr1) {
            int idx = Arrays.binarySearch(arr2, a);
            if (idx < 0) {
                idx = -idx - 1;
            }
            
            // Check adjacent elements
            if ((idx == n2 || arr2[idx] - a > d) && (idx == 0 || a - arr2[idx - 1] > d)) {
                count++;
            }
        }
        
        return count;
    }
}