class Solution {
    public boolean checkDivisibility(int n) {
        int temp = n;
        int sum = 0; int prod = 1;
        while(temp > 0){
            int dig = temp % 10;
            sum+=dig;
            prod *= dig;
            temp /= 10;
            
        }
        int finalSum = sum + prod;
        if(n % finalSum == 0 ) {
            return true;
        }
        else{
            return false;
        }
    }
}