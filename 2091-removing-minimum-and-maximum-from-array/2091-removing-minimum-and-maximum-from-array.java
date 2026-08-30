class Solution {
    public int minimumDeletions(int[] nums) {
        int n=nums.length;
        int minindex=0;
        int maxindex=0;
        for(int i=0;i<n;i++)
        {
            if(nums[i]<nums[minindex])
            {
                minindex=i;
            }
            if(nums[i]>nums[maxindex])
            {
                maxindex=i;
            }
        }
        int left=Math.max(minindex,maxindex)+1;
        int right=n-Math.min(minindex,maxindex);
        int both=Math.min(minindex,maxindex)+1+n-Math.max(minindex,maxindex);

        return Math.min(left,Math.min(right,both));
    }
}