import java.util.*;

class Solution {

    // Custom Multiset helper using TreeMap to handle duplicates efficiently
    private static class FrequencyMap {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        long sum = 0;
        int size = 0;

        void add(int val) {
            map.merge(val, 1, Integer::sum);
            sum += val;
            size++;
        }

        void remove(int val) {
            map.merge(val, -1, Integer::sum);
            if (map.get(val) == 0) {
                map.remove(val);
            }
            sum -= val;
            size--;
        }

        boolean contains(int val) {
            return map.containsKey(val);
        }

        int lastKey() {
            return map.lastKey();
        }

        int firstKey() {
            return map.firstKey();
        }
    }

    private FrequencyMap lower = new FrequencyMap();
    private FrequencyMap upper = new FrequencyMap();

    private void balance() {
        while (lower.size < upper.size) {
            int val = upper.firstKey();
            upper.remove(val);
            lower.add(val);
        }
        while (lower.size > upper.size + 1) {
            int val = lower.lastKey();
            lower.remove(val);
            upper.add(val);
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] minOps = new long[n - x + 1];

        // Reset state for each test case
        lower = new FrequencyMap();
        upper = new FrequencyMap();

        for (int i = 0; i < n; i++) {
            if (lower.size == 0 || nums[i] <= lower.lastKey()) {
                lower.add(nums[i]);
            } else {
                upper.add(nums[i]);
            }
            balance();

            if (i >= x) {
                int out = nums[i - x];
                if (lower.contains(out)) {
                    lower.remove(out);
                } else {
                    upper.remove(out);
                }
                balance();
            }

            if (i >= x - 1) {
                long median = lower.lastKey();
                long cost = (median * lower.size - lower.sum) + (upper.sum - median * upper.size);
                minOps[i - x + 1] = cost;
            }
        }

        // DP with memoization
        Long[][] memo = new Long[n + 1][k + 1];
        return dp(0, k, x, n, minOps, memo);
    }

    private long dp(int idx, int remK, int x, int n, long[] minOps, Long[][] memo) {
        if (remK == 0) return 0;
        if (idx > n - x * remK) return (long) 1e18; // Infinity representation
        if (memo[idx][remK] != null) return memo[idx][remK];

        // Choice 1: Skip index
        long res = dp(idx + 1, remK, x, n, minOps, memo);

        // Choice 2: Pick window of size x starting at idx
        res = Math.min(res, minOps[idx] + dp(idx + x, remK - 1, x, n, minOps, memo));

        return memo[idx][remK] = res;
    }
}