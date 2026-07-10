import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Sort and deduplicate in place
        int[] vals = nums.clone();
        Arrays.sort(vals);
        int m = 0;
        for (int x : vals) {
            if (m == 0 || vals[m - 1] != x) {
                vals[m++] = x;
            }
        }
        
        // Step 2: O(N) Two-Pointer approach for the next optimal jump
        int[] nextHop = new int[m];
        int right = 0;
        for (int left = 0; left < m; left++) {
            while (right + 1 < m && vals[right + 1] - vals[left] <= maxDiff) {
                right++;
            }
            nextHop[left] = right;
        }
        
        // Step 3: Build the Binary Lifting table
        int LOG = 18; 
        int[][] up = new int[LOG][m];
        up[0] = nextHop; // Directly reference the array for the 0th power to save time
        
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < m; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }
        
        // Step 4: Process queries using native binary search
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];
            
            if (u == v) {
                ans[q] = 0;
                continue;
            }
            
            int valU = nums[u], valV = nums[v];
            if (valU == valV) {
                ans[q] = 1; // Distinct nodes with the same value form a clique
                continue;
            }
            
            // Native binary search works because we are guaranteed the values exist in `vals`
            int curr = Arrays.binarySearch(vals, 0, m, Math.min(valU, valV));
            int targetIdx = Arrays.binarySearch(vals, 0, m, Math.max(valU, valV));
            
            // Check max reachability to see if a path exists at all
            int maxReachable = curr;
            for (int k = LOG - 1; k >= 0; k--) {
                maxReachable = up[k][maxReachable];
            }
            
            if (maxReachable < targetIdx) {
                ans[q] = -1;
                continue;
            }
            
            // Jump efficiently using the lifting table
            int steps = 0;
            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][curr] < targetIdx) {
                    steps += (1 << k);
                    curr = up[k][curr];
                }
            }
            
            ans[q] = steps + 1;
        }
        
        return ans;
    }
}