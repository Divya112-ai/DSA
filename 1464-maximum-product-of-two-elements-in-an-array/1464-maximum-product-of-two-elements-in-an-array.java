class Solution {
    public int maxProduct(int[] nums) {
        int max1=0;
        int max2=0;
        for(int n:nums)
        {
            max2=Math.max(max2,Math.min(max1,n));
            max1=Math.max(max1,n);

        }
        return (max1-1)*(max2-1);
    }
}