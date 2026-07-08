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

        // Count non-zero digits to size our arrays exactly
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') count++;
        }

        // 2. Precompute indices and prefix arrays
        int[] indices = new int[count];
        long[] prefSum = new long[count + 1];
        long[] prefVal = new long[count + 1];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c != '0') {
                int digit = c - '0';
                indices[idx] = i;
                prefSum[idx + 1] = prefSum[idx] + digit;
                prefVal[idx + 1] = (prefVal[idx] * 10 + digit) % mod;
                idx++;
            }
        }

        // 3. Process Queries
        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int L = queries[i][0];
            int R = queries[i][1];

            // Find the start and end of non-zero digits in this range
            int a = lowerBound(indices, L);
            int b = upperBound(indices, R) - 1;

            // If no non-zero digits are in the range [L, R]
            if (a > b) {
                answer[i] = 0;
                continue;
            }

            // Calculate Sum
            long sum = prefSum[b + 1] - prefSum[a];

            // Calculate concatenated value x
            int len = b - a + 1;
            long x = (prefVal[b + 1] - (prefVal[a] * pow10[len]) % mod + mod) % mod;

            // Store final modulo answer
            answer[i] = (int) ((x * sum) % mod);
        }

        return answer;
    }

    // Helper: Finds the first index where the value is >= target
    private int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // Helper: Finds the first index where the value is > target
    private int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}