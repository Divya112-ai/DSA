
class Solution {
    public void moveZeroes(int[] nums) {
        int count=0;
       
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]==0)
            {
                continue;
            }
            else
            {
                nums[count]=nums[i];
                count++;
            }
        }
        for(int i=count;i<nums.length;i++)
        {
            nums[count]=0;
            count++;
        }
        System.out.println(nums);
    }
}