import java.util.*;

class Solution {

    private static class Element implements Comparable<Element> {
        long val;
        int idx;

        Element(long val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        @Override
        public int compareTo(Element other) {
            if (this.val != other.val) {
                return Long.compare(this.val, other.val);
            }
            return Integer.compare(this.idx, other.idx);
        }
    }

    private TreeSet<Element> lower;
    private TreeSet<Element> upper;
    private long lowerSum;
    private long upperSum;

    private void balance() {
        while (lower.size() < upper.size()) {
            Element first = upper.first();
            upper.remove(first);
            upperSum -= first.val;
            lower.add(first);
            lowerSum += first.val;
        }
        while (lower.size() > upper.size() + 1) {
            Element last = lower.last();
            lower.remove(last);
            lowerSum -= last.val;
            upper.add(last);
            upperSum += last.val;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] minOps = new long[n - x + 1];

        // Reset state for each test case
        lower = new TreeSet<>();
        upper = new TreeSet<>();
        lowerSum = 0;
        upperSum = 0;

        for (int i = 0; i < n; i++) {
            Element curr = new Element(nums[i], i);
            if (lower.isEmpty() || curr.compareTo(lower.last()) <= 0) {
                lower.add(curr);
                lowerSum += curr.val;
            } else {
                upper.add(curr);
                upperSum += curr.val;
            }
            balance();

            if (i >= x) {
                Element out = new Element(nums[i - x], i - x);
                if (lower.contains(out)) {
                    lower.remove(out);
                    lowerSum -= out.val;
                } else {
                    upper.remove(out);
                    upperSum -= out.val;
                }
                balance();
            }

            if (i >= x - 1) {
                long median = lower.last().val;
                long cost = (median * lower.size() - lowerSum) + (upperSum - median * upper.size());
                minOps[i - x + 1] = cost;
            }
        }

        // DP with memoization
        Long[][] memo = new Long[n + 1][k + 1];
        return dp(0, k, x, n, minOps, memo);
    }

    private long dp(int idx, int remK, int x, int n, long[] minOps, Long[][] memo) {
        if (remK == 0) return 0;
        if (idx > n - x * remK) return (long) 1e18; // Infinity
        if (memo[idx][remK] != null) return memo[idx][remK];

        // Choice 1: Skip index
        long res = dp(idx + 1, remK, x, n, minOps, memo);

        // Choice 2: Pick window of size x starting at idx
        res = Math.min(res, minOps[idx] + dp(idx + x, remK - 1, x, n, minOps, memo));

        return memo[idx][remK] = res;
    }
}