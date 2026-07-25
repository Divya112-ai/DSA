class Solution {
    public int maxProduct(int n) {
        int one=0;
        int two=0;
        while(n>0)
        {
            int digit=n%10;
            if(digit>one)
            {
                two=one;
                one=digit;
            }
            else if(digit>two)
            {
                two=digit;
            }
            n=n/10;
        }
        return one*two;
    }
}