class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int maxScore = Integer.MIN_VALUE;
        int m = values[0];
        for(int i = 1; i < values.length; i++){
            maxScore = Math.max(maxScore, values[i] - i + m);
            m = Math.max(m,values[i] + i);

        }return maxScore;
    }
}