class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int[] answer=new int[nums.length];
        int productR=1;
        int productL=1;
        for(int i=n-1;i>=0;i--)
        {
            answer[i]=productR;
            productR=productR*nums[i];
        }
        for(int i=0;i<nums.length;i++)
        {
            
            answer[i]=answer[i]*productL;
            productL=productL*nums[i];
        }
        return answer;
    }
}