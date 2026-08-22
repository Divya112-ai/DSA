class Solution {
    public boolean checkDivisibility(int n) {
        int num=n;
        int sum=0;
        int product=1;
        while(n>0)
        {
            int curr=n%10;
            n/=10;
            sum+=curr;
            product*=curr;

        }
        return num %(sum+product)==0;
    }
}