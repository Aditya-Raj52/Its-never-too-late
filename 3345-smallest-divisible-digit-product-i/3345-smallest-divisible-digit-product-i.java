class Solution {

        public int digProd(int x){
            int Prod = 1;
            while(x > 0){
                int dig = x % 10;
                Prod *= dig;
                x /= 10;
            }
            return Prod;
        }

    public int smallestNumber(int n, int t) {
        int digProd = 1;


        if(digProd(n) % t == 0){
            return n;
        }
        while (digProd(n) % t != 0){
            n++;
        }
        return n;
        
    }
}