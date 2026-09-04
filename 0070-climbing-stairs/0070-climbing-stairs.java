class Solution {
    public int climbStairs(int n) {
        if(n <= 2)return n;
        int prev2 = 1;
        int prev = 2;
        int current = 0;
        for(int i = 3; i <= n; i++){
           current = prev + prev2;
           prev2 = prev;
           prev = current;

        }
        return prev;

         
    }
}