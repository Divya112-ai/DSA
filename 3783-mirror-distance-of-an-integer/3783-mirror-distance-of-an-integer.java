class Solution {
    public int mirrorDistance(int n) {
      int original = n;
        int reverse = 0;

        while (n > 0) {
            int digit = n % 10;          // Get last digit
            reverse = reverse * 10 + digit;
            n = n / 10;                  // Remove last digit
        }

        return Math.abs(original - reverse);  
    }
}