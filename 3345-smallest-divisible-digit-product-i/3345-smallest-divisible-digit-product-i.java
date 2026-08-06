class Solution {
    static int prod(int n){
        if(n%10 == n) return n;
        return (n%10) * prod(n/10);
    }
    public int smallestNumber(int n, int t) {
        while(true){
            if(prod(n)%t==0){
                return n;
            }
            n++;
        }
    }
}