class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        List<Integer> timeRequiredList = new ArrayList<>();

        for(int i=0; i<dist.length; i++) {
            int timeRequired = (int)Math.ceil((double)dist[i]/speed[i]);
            timeRequiredList.add(timeRequired);
        }
        timeRequiredList.sort((a,b) -> a-b);

        for(int i=0; i<timeRequiredList.size(); i++) {
            if(timeRequiredList.get(i) <= i) {
                return i;
            }
        }
        return timeRequiredList.size();
    }
}