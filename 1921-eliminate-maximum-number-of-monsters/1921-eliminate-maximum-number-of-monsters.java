class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int count = 0;
        double arrivalTime[] = new double[dist.length];
        for(int i = 0; i < dist.length; i++){
            arrivalTime[i] = (double) dist[i] / speed[i];
              
        }
        Arrays.sort(arrivalTime);
        for(int i = 0; i < arrivalTime.length; i++){
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