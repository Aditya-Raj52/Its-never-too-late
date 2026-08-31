class Solution {
    int ans=0;
    public int reversePairs(int[] nums) {        
        int n = nums.length;
        reversePairs(nums,0,n);
        return ans;
    }

    public int[] reversePairs(int[] nums,int str, int end) {  
        if(end-str==1){
            return new int[]{nums[str]};
        }
        int mid = (str+end)/2;
        int[] srtArr = new int[end-str];
        int[] left = reversePairs(nums,str,mid);
        int[] right = reversePairs(nums,mid,end);
        int p1=0;
        int p2=0;
        int cnt=0;
        int m =left.length;
        int n= right.length;
        while(p1<m && p2<n){
            if(left[p1]>2L*right[p2]){
                cnt+=m-p1;
                p2++;
            }
            else{
                p1++;
            }
        }
        ans+=cnt;
        p1=0;
        p2=0;
        cnt=0;
        while(p1<m && p2<n){
            if(left[p1]<=right[p2]){
                srtArr[cnt]=left[p1];
                p1++;
                cnt++;
            }
            else{
                srtArr[cnt]=right[p2];
                p2++;
                cnt++;
            }
        }
        if(p1==m){
            while(p2<n){
                srtArr[cnt]=right[p2];
                cnt++;
                p2++;
            }
        }
        else{
            while(p1<m){
                srtArr[cnt]=left[p1];
                cnt++;
                p1++;
            }
        }

        return srtArr;
    }
}