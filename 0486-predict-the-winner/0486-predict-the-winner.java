class Solution {
    int[][] dp;
    public int helper(int[] nums,int left,int right)
    {
        if(left>right)
        {
            return 0;
        }
        if(dp[left][right] !=Integer.MIN_VALUE)
        {
            return dp[left][right];
        }
        int leftRec=nums[left]-helper(nums,left+1,right);
        int rightRec=nums[right]-helper(nums,left,right-1);
        return dp[left][right]=Math.max(leftRec,rightRec);
       
    }
    public boolean predictTheWinner(int[] nums) {
        int n=nums.length;
        dp=new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                dp[i][j]=Integer.MIN_VALUE;
            }
        }
        return helper(nums,0,n-1)>=0;
    }
}