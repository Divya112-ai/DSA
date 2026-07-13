class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int low=0,high;
        int minlenWindow=Integer.MAX_VALUE;
        int currsum=0;
        for(high=0;high<nums.length;high++)
        {
            currsum+=nums[high];
            while(currsum>=target)
            {
                int currwindow=high-low+1;
                minlenWindow=Math.min(currwindow,minlenWindow);
                currsum=currsum-nums[low];
                low++;
            }
        }
        return minlenWindow=(minlenWindow==Integer.MAX_VALUE)?0:minlenWindow;
    }
}