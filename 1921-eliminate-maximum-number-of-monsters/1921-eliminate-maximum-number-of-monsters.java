class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] arrivalCount = new int[n + 1];

        // Step 1: Calculate arrival times and increment bucket counts
        for (int i = 0; i < n; i++) {
            // Equivalent to ceil(dist[i] / speed[i]) using integer arithmetic
            int arrivalTime = (dist[i] + speed[i] - 1) / speed[i];
            
            if (arrivalTime >= n) {
                arrivalCount[n]++;
            } else {
                arrivalCount[arrivalTime]++;
            }
        }

        // Step 2: Accumulate counts and check if monsters reach the city
        int monstersArrived = 0;
        for (int minute = 0; minute < n; minute++) {
            monstersArrived += arrivalCount[minute];
            
            // If total monsters that arrived by this minute exceeds the weapon charges used (minute), you lose
            if (monstersArrived > minute) {
                return minute;
            }
        }

        return n;
    }
}