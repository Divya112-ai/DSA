import java.util.Arrays;
class Solution {
    public int thirdMax(int[] nums) {
      int n=nums.length;
      int count=0;
      //Arrays.reverse.(sort(nums));
       Arrays.sort(nums);
       for (int i = 0; i < nums.length / 2; i++) {
            int temp = nums[i];
            nums[i] = nums[nums.length - 1 - i];
            nums[nums.length - 1 - i] = temp;
        }

      int max=nums[0];
      for(int i=0;i<n;i++)
      {
        if(i<n-1&&nums[i]==nums[i+1])
        {
            continue;
        }
        else
        {
            count++;
            if(count==3)
            {
                return nums[i];
            }
        }
      }  
      return max;
    }
}