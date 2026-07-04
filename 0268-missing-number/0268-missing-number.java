import java.util.Arrays;
class Solution {
   int missingNumber(int[] nums) {
        int sum=0,asum=0;
        int n=nums.length;
        for(int i=0;i<=n;i++)
        {
            asum+=i;
        }
        for(int a:nums)
        {
            sum+=a;
        }
        return asum-sum;
    }
   }