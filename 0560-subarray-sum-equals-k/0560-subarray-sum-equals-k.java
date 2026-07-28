import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        // Track the total number of valid subarrays found
        int count = 0;
        
        // Track the running total as we walk through the array
        int currentSum = 0;
        
        // Key: A past running sum | Value: How many times we have seen that sum
        Map<Integer, Integer> seenSums = new HashMap<>();
        
        // Base case: A running sum of 0 has occurred 1 time (before we start)
        // This ensures subarrays starting from index 0 are correctly counted
        seenSums.put(0, 1);
        
        for (int num : nums) {
            // Update the running total
            currentSum += num;
            
            // Check if the "missing piece" exists in our past
            int targetPastSum = currentSum - k;
            if (seenSums.containsKey(targetPastSum)) {
                count += seenSums.get(targetPastSum);
            }
            
            // Record this current running sum into the map for future steps
            seenSums.put(currentSum, seenSums.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
}