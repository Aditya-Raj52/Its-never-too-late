class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
       if (n == 0) return 0;

        // Arrays to store profit for future days
        // ahead → profit for the next day
        // cur → profit for the current day being processed
        int[] ahead = new int[2]; // initialized to 0
        int[] cur = new int[2];   // initialized to 0

        // Base condition: no profit if no transactions can be made
        ahead[0] = ahead[1] = 0;

        // Iterate from the last day to the first day
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                int profit;
                if (buy == 0) {
                    // Option 1: skip buying
                    // Option 2: buy today → subtract price and move to selling state
                    profit = Math.max(0 + ahead[0], -prices[ind] + ahead[1]);
                } else {
                    // Option 1: skip selling
                    // Option 2: sell today → add price and move to buying state
                    profit = Math.max(0 + ahead[1], prices[ind] + ahead[0]);
                }
                cur[buy] = profit;
            }
            // Update ahead for the next iteration
            ahead = cur.clone();
        }

        // Maximum profit is when we start at day 0 with buying allowed
        return cur[0];
    }
}