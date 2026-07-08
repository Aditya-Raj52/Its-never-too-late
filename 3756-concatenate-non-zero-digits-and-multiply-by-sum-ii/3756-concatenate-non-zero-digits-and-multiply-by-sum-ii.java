class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int mod = 1_000_000_007;

        // 1. Precompute powers of 10
        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % mod;
        }

        // 2. Build 1-indexed prefix arrays mapped to the original string
        long[] prefSum = new long[n + 1];
        long[] prefVal = new long[n + 1];
        int[] count = new int[n + 1]; // Tracks how many non-zeros we've seen

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int digit = c - '0';
            
            // Carry over previous state by default
            prefSum[i + 1] = prefSum[i];
            prefVal[i + 1] = prefVal[i];
            count[i + 1] = count[i];

            // If non-zero, update the current state
            if (digit != 0) {
                prefSum[i + 1] += digit;
                prefVal[i + 1] = (prefVal[i] * 10 + digit) % mod;
                count[i + 1]++;
            }
        }

        // 3. Process Queries in O(1) time each
        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int L = queries[i][0];
            int R = queries[i][1];

            // How many non-zero digits are in this substring?
            int len = count[R + 1] - count[L];
            
            if (len == 0) {
                answer[i] = 0;
                continue;
            }

            // O(1) lookups
            long sum = prefSum[R + 1] - prefSum[L];
            long x = (prefVal[R + 1] - (prefVal[L] * pow10[len]) % mod + mod) % mod;

            answer[i] = (int) ((x * sum) % mod);
        }

        return answer;
    }
}