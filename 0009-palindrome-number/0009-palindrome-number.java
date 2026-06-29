class Solution {
    public boolean isPalindrome(int x) {
        int ori=x;
        int rev=0;
        while(x!=0)
        {
            int digit=x%10;
            rev=rev*10+digit;
            x=x/10;
        }
    
        boolean res=(ori==rev&& rev>=0)?  true: false;
        return res;
    }
}