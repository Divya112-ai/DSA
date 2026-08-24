class Solution {
    public int findNthDigit(int n) {
        
        long digits = 1;   // Number of digits per number
        long count = 9;    // How many numbers in this group
        long start = 1;    // First number of this group

        // Find the group containing the nth digit
        while (n > digits * count) {
            n -= digits * count;
            
            digits++;
            count *= 10;
            start *= 10;
        }

        // Find the actual number
        long num = start + (n - 1) / digits;

        // Find the digit inside that number
        int index = (int) ((n - 1) % digits);

        return String.valueOf(num).charAt(index) - '0';
    }
}