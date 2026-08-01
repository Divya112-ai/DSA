class Solution {
    public int largestAltitude(int[] gain) {
        int n=gain.length;
        int res=0;
        int cur=0;
        for(int i=0;i<n;i++)
        {
            cur+=gain[i];
            res=Math.max(res,cur);
        }
        return res;
    }
}