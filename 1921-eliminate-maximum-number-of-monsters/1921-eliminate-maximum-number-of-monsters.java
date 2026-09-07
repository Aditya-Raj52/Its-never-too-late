class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int count = 0;
        double arrivalTime[] = new double[n];
        for(int i = 0; i < n; i++){
            arrivalTime[i] = (double) dist[i] / speed[i];
              
        }
        Arrays.sort(arrivalTime);
        for(int i = 0; i < n; i++){
            if(i < arrivalTime[i]){
                count++;
            }
            else{
                break;
            }
        } 
        return count;
    }
}