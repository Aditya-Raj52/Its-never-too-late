class Solution {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();

        int[] fact = new int[n];

        fact[0] = 1;
        for(int i = 1; i < n; i++){
            fact[i] = fact[i - 1] * i;
            nums.add(i);
        }
        nums.add(n);
        k--;
        StringBuilder s = new StringBuilder();
        for (int i = n; i > 0; i--){
            int idx = k / fact[i - 1];
            s.append(nums.get(idx));
            nums.remove(idx);
            k %= fact[i - 1];
        }
        return s.toString();

        
    }
}