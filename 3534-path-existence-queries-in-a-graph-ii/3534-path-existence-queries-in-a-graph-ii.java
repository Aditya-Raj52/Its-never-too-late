class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
int[] temp = nums.clone();
        Arrays.sort(temp);
        int m = 0;
        
        // Remove duplicates in-place to get unique values
        for (int i = 0; i < n; i++) {
            if (i == 0 || temp[i] != temp[i - 1]) {
                temp[m++] = temp[i];
            }
        }
        int[] uniqueVals = Arrays.copyOf(temp, m);
        
        // Step 2: Compute the immediate next optimal jump for each unique value
        int[] nextHop = new int[m];
        for (int i = 0; i < m; i++) {
            int target = uniqueVals[i] + maxDiff;
            // Find the largest element <= target
            int idx = upperBound(uniqueVals, target) - 1;
            nextHop[i] = idx;
        }
        
        // Step 3: Build the Binary Lifting table
        int LOG = 18; // Sufficient for n <= 10^5
        int[][] up = new int[LOG][m];
        
        for (int i = 0; i < m; i++) {
            up[0][i] = nextHop[i];
        }
        
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < m; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }
        
        // Step 4: Process each query
        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];
            
            if (u == v) {
                ans[q] = 0;
                continue;
            }
            
            int valU = nums[u];
            int valV = nums[v];
            
            if (valU == valV) {
                ans[q] = 1; // Distinct nodes with the same value form a clique
                continue;
            }
            
            // Ensure we always jump from the smaller value to the larger value
            int lowVal = Math.min(valU, valV);
            int highVal = Math.max(valU, valV);
            
            // Get their corresponding indices in the unique sorted array
            int curr = lowerBound(uniqueVals, lowVal);
            int targetIdx = lowerBound(uniqueVals, highVal);
            
            // Check if target is reachable at all by simulating max possible jumps
            int maxReachable = curr;
            for (int k = LOG - 1; k >= 0; k--) {
                maxReachable = up[k][maxReachable];
            }
            
            if (maxReachable < targetIdx) {
                ans[q] = -1;
                continue;
            }
            
            // Use binary lifting to count the minimum steps required
            int steps = 0;
            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][curr] < targetIdx) {
                    steps += (1 << k);
                    curr = up[k][curr];
                }
            }
            
            // One more step is needed to cross or touch the targetIdx
            ans[q] = steps + 1;
        }
        
        return ans;
    }
    
    // Helper method: Equivalent to Python's bisect_left
    private int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // Helper method: Equivalent to Python's bisect_right
    private int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}