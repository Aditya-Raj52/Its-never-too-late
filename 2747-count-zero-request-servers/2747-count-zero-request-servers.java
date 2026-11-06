import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int lLen = logs.length;
        // Pack logs into a 1D long array: (time << 32) | serverId
        long[] packedLogs = new long[lLen];
        for (int i = 0; i < lLen; i++) {
            packedLogs[i] = ((long) logs[i][1] << 32) | (logs[i][0] & 0xFFFFFFFFL);
        }
        Arrays.sort(packedLogs);

        int m = queries.length;
        // Pack queries similarly: (time << 32) | originalIndex
        long[] packedQueries = new long[m];
        for (int i = 0; i < m; i++) {
            packedQueries[i] = ((long) queries[i] << 32) | i;
        }
        Arrays.sort(packedQueries);

        int[] ans = new int[m];
        int[] count = new int[n + 1];
        int activeServers = 0;
        
        int left = 0, right = 0;

        for (int i = 0; i < m; i++) {
            long q = packedQueries[i];
            int qTime = (int) (q >>> 32);
            int qIdx = (int) q;
            
            int windowStart = qTime - x;
            int windowEnd = qTime;

            // Expand right pointer
            while (right < lLen && (packedLogs[right] >>> 32) <= windowEnd) {
                int sId = (int) packedLogs[right];
                if (count[sId] == 0) {
                    activeServers++;
                }
                count[sId]++;
                right++;
            }

            // Shrink left pointer
            while (left < lLen && (packedLogs[left] >>> 32) < windowStart) {
                int sId = (int) packedLogs[left];
                count[sId]--;
                if (count[sId] == 0) {
                    activeServers--;
                }
                left++;
            }

            ans[qIdx] = n - activeServers;
        }

        return ans;
    }
}