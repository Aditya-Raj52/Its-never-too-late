class Solution {
    public boolean canMakeSubsequence(String s, String t) {
        // Renaming to match problem statement: s is candidate subsequence, t is target string
        // Note: Check length constraint
        int n = s.length();
        int m = t.length();

        if (n > m) {
            return false;
        }

        int[] left = new int[n];
        int[] right = new int[n];

        // 1. Compute left array (earliest matching index in t for prefix s[0..i])
        int ptr = 0;
        for (int i = 0; i < n; i++) {
            while (ptr < m && t.charAt(ptr) != s.charAt(i)) {
                ptr++;
            }
            left[i] = ptr;
            if (ptr < m) {
                ptr++; // move to next character in t for future matches
            }
        }

        // If s is already a subsequence of t without any replacements
        if (left[n - 1] < m) {
            return true;
        }

        // 2. Compute right array (latest matching index in t for suffix s[i..n-1])
        ptr = m - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (ptr >= 0 && t.charAt(ptr) != s.charAt(i)) {
                ptr--;
            }
            right[i] = ptr;
            if (ptr >= 0) {
                ptr--; // move to previous character in t
            }
        }

        // 3. Check if replacing s[i] creates a valid subsequence
        for (int i = 0; i < n; i++) {
            int L = (i == 0) ? -1 : left[i - 1];
            int R = (i == n - 1) ? m : right[i + 1];

            // Ensure prefix and suffix can be matched and there's space for s[i] in t
            if (L < m && R >= 0 && (R - L >= 2)) {
                return true;
            }
        }

        return false;
    }
}