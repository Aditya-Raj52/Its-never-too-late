import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        
        // Store queries with their original indices
        int[][] indexedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            indexedQueries[i][0] = queries[i];
            indexedQueries[i][1] = i;
        }
        
        // Sort queries by time ascending
        Arrays.sort(indexedQueries, Comparator.comparingInt(a -> a[0]));
        
        // Sort logs by time ascending
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));
        
        int[] ans = new int[m];
        
        // Use a frequency array instead of HashMap for O(1) lightning-fast updates
        int[] serverCount = new int[n + 1];
        int activeServers = 0; // Tracks how many servers have at least 1 request
        
        int left = 0, right = 0;
        int numLogs = logs.length;
        
        for (int[] q : indexedQueries) {
            int qTime = q[0];
            int qIdx = q[1];
            
            int windowStart = qTime - x;
            int windowEnd = qTime;
            
            // Add logs falling within the window end
            while (right < numLogs && logs[right][1] <= windowEnd) {
                int sId = logs[right][0];
                if (serverCount[sId] == 0) {
                    activeServers++;
                }
                serverCount[sId]++;
                right++;
            }
            
            // Remove logs falling outside the window start
            while (left < numLogs && logs[left][1] < windowStart) {
                int sId = logs[left][0];
                serverCount[sId]--;
                if (serverCount[sId] == 0) {
                    activeServers--;
                }
                left++;
            }
            
            // Zero request servers = Total servers - Active servers
            ans[qIdx] = n - activeServers;
        }
        
        return ans;
    }
}