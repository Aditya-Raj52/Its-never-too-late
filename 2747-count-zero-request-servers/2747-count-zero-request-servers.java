import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        
        // Step 1: Pair queries with their original indices
        int[][] indexedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            indexedQueries[i][0] = queries[i];
            indexedQueries[i][1] = i;
        }
        
        // Step 2: Sort queries by their time values ascending
        Arrays.sort(indexedQueries, Comparator.comparingInt(a -> a[0]));
        
        // Step 3: Sort logs by time ascending
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));
        
        int[] ans = new int[m];
        Map<Integer, Integer> serverCount = new HashMap<>();
        
        int left = 0, right = 0;
        int numLogs = logs.length;
        
        // Step 4: Process each query
        for (int[] q : indexedQueries) {
            int qTime = q[0];
            int qIdx = q[1];
            
            int windowStart = qTime - x;
            int windowEnd = qTime;
            
            // Expand the right side of the window to include all logs <= windowEnd
            while (right < numLogs && logs[right][1] <= windowEnd) {
                serverCount.put(logs[right][0], serverCount.getOrDefault(logs[right][0], 0) + 1);
                right++;
            }
            
            // Shrink the left side of the window to remove logs < windowStart
            while (left < numLogs && logs[left][1] < windowStart) {
                int sId = logs[left][0];
                int count = serverCount.get(sId);
                if (count == 1) {
                    serverCount.remove(sId);
                } else {
                    serverCount.put(sId, count - 1);
                }
                left++;
            }
            
            // The number of servers with zero requests is total servers minus active servers in the map
            ans[qIdx] = n - serverCount.size();
        }
        
        return ans;
    }
}