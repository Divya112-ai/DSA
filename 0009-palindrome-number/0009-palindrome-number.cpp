class Solution {
public:
    bool isPalindrome(int x) {

        // Negative numbers are not palindromes
        if (x < 0) {
            return false;
        }

        // Numbers ending in 0 cannot be palindrome
        // except 0 itself
        if (x != 0 && x % 10 == 0) {
            return false;
        }

        int rev = 0;

        // Reverse only half of x
        while (x > rev) {
            int digit = x % 10;
            rev = rev * 10 + digit;
            x /= 10;
        }

        // Even number of digits: x == rev
        // Odd number of digits: x == rev / 10
        return x == rev || x == rev / 10;
    }
};