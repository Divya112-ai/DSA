class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        long totalSubarrays = 0;
        
        // Initialize evenCount to 1 to represent an empty prefix sum of 0 (which is even).
        // This allows single-element odd subarrays to be counted properly.
        int evenCount = 1; 
        int oddCount = 0;
        int prefixSum = 0;
        
        for (int num : arr) {
            prefixSum += num;
            
            if (prefixSum % 2 != 0) {
                // Current prefix sum is odd: pair with all previous even prefix sums
                totalSubarrays = (totalSubarrays + evenCount) % MOD;
                oddCount++;
            } else {
                // Current prefix sum is even: pair with all previous odd prefix sums
                totalSubarrays = (totalSubarrays + oddCount) % MOD;
                evenCount++;
            }
        }
        
        return (int) totalSubarrays;
    }
}
