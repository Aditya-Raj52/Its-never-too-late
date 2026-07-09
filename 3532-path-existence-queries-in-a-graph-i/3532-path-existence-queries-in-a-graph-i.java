class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] componentIds = new int[n];
        int currentId = 0;

        for( int i  = 1; i < n; i++){
            if(nums[i] - nums[i - 1] > maxDiff){
                currentId++;
            }
            componentIds[i] = currentId;
        }

        boolean[] answer = new boolean[queries.length];

        for(int i = 0; i < queries.length; i++){
            int u = queries[i][0];
            int v = queries[i][1];

            answer[i] = (componentIds[u] == componentIds[v]);
        }

        return answer;

        
    }
}