class Solution {
    public int longestSubsequence(int[] nums) {
        int xor=0;
        boolean Nonzero=false;
        for(int n:nums)
        {
            xor^=n;
            if(n!=0)
            {
                Nonzero=true;
            }
        }
        if(xor!=0)
        {
            return nums.length;
        }
        if(Nonzero)
        {
            return nums.length-1;
        }
        return 0;

    }
}