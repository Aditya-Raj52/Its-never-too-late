import java.util.Arrays;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Pair values with original indexes and sort to get absolute ranks
        long[] paired = new long[n];
        for (int i = 0; i < n; i++) {
            // Pack value in higher 32 bits, original index in lower 32 bits for clean sorting
            paired[i] = ((long) nums[i] << 32) | i;
        }
        Arrays.sort(paired);

        int[] sortedNums = new int[n];
        int[] indexMap = new int[n]; // Maps original index -> sorted position index
        for (int i = 0; i < n; i++) {
            sortedNums[i] = (int) (paired[i] >> 32);
            int originalIdx = (int) (paired[i] & 0xFFFFFFFFL);
            indexMap[originalIdx] = i;
        }

        // Step 2: Compute dynamic LOG depth limit and flatten the binary lifting array
        int log = 32 - Integer.numberOfLeadingZeros(n);
        int[] up = new int[log * n]; // Flattened 1D array for cache-locality optimization

        // O(N) Two-Pointer sweep to get the base next-hop jump (level 0)
        int right = 0;
        for (int left = 0; left < n; left++) {
            while (right + 1 < n && sortedNums[right + 1] - sortedNums[left] <= maxDiff) {
                right++;
            }
            up[left] = right; // up[0 * n + left] = right
        }

        // Compute power-of-two jumps
        for (int k = 1; k < log; k++) {
            int currRow = k * n;
            int prevRow = (k - 1) * n;
            for (int i = 0; i < n; i++) {
                up[currRow + i] = up[prevRow + up[prevRow + i]];
            }
        }

        // Step 3: Fast O(1) index routing + O(log N) jump sequence per query
        int numQueries = queries.length;
        int[] ans = new int[numQueries];

        for (int q = 0; q < numQueries; q++) {
            int u = queries[q][0];
            int v = queries[q][1];

            if (u == v) {
                ans[q] = 0;
                continue;
            }

            // Route to sorted coordinate indices instantly
            int posU = indexMap[u];
            int posV = indexMap[v];

            if (posU == posV) {
                ans[q] = 1;
                continue;
            }

            int curr = Math.min(posU, posV);
            int targetIdx = Math.max(posU, posV);

            // Quick connectivity check: Can we reach the target utilizing max jump power?
            int maxReachable = curr;
            for (int k = log - 1; k >= 0; k--) {
                maxReachable = up[k * n + maxReachable];
            }
            if (maxReachable < targetIdx) {
                ans[q] = -1;
                continue;
            }

            // Binary lifting execution loop
            int steps = 0;
            for (int k = log - 1; k >= 0; k--) {
                int nextPos = up[k * n + curr];
                if (nextPos < targetIdx) {
                    steps += (1 << k);
                    curr = nextPos;
                }
            }

            ans[q] = steps + 1;
        }

        return ans;
    }
}