import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        // Map stores: <PrefixSum, Frequency>
        Map<Integer, Integer> map = new HashMap<>();
        // Base case: A prefix sum of 0 has occurred once (before starting)
        map.put(0, 1);
        for (int num : nums) {
            currentSum += num;
            // If (currentSum - k) exists in map, add its frequency to count
            if (map.containsKey(currentSum - k)) {
                count += map.get(currentSum - k);
            }
            // Record current sum in the hash map
            map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }
}