class Solution {
    public int majorityElement(int[] nums) {
        int n=nums.length;
        int major=0;
    for(int val:nums)
      {
        int freq=0;
        for(int ele:nums)
        {
            if(ele==val)
            {
                freq++;
            }
        }
        if(freq>n/2)
            return val;
      }
       return major;
      
    }
   
}