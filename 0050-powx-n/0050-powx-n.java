class Solution {
    public double myPow(double x, int n) {
        long pw = n;

       
        if (pw < 0) {
            x = 1 / x;
            pw = -pw;
        }

        double result = 1;

        while (pw > 0) {

           
            if (pw % 2 == 1) {
                result *= x;
            }

            
            x *= x;

            
            pw /= 2;
        }

        return result;

    }
}
