class Solution {
    public int minimumPushes(String word) {
       int n = word.length();
       int totalPushes = 0;
       
       if(n < 8){
        return n;
       }

       for(int i = 0; i < n; i++){
        totalPushes += (i / 8) + 1;
       }

       return totalPushes;
    }
}