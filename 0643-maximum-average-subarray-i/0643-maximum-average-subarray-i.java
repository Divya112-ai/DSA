class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n=nums.length;
        int csum=0,maxsum=0;
        for(int i=0;i<k;i++)
        {
            csum+=nums[i];
        }
        maxsum=csum;
        for(int i=k;i<n;i++)
        {
            csum=csum+nums[i]-nums[i-k];
            if(csum>maxsum)
            {
                maxsum=csum;
            }
        }
        return (double)maxsum/k;
    }
}