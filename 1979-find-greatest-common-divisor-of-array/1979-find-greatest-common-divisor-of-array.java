class Solution {
    public int findGCD(int[] nums) {
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for(int n:nums)
        {
            min=Math.min(min,n);
            max=Math.max(max,n);
        }
        return gcd(max,min);
    }
    public int gcd(int a,int b)
    {
        while(b!=0)
        {
            int temp=b;
            b=a%b;
            a=temp;
        }
        return a;
    }
}