class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;

        // Outer loop picks the starting index of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;

            // Inner loop expands the subarray to the right
            for (int j = i; j < n; j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;

                // Stop expanding if the subarray becomes longer than r
                if (length > r) {
                    break;
                }

                // Check if length is within [l, r] and sum is strictly positive
                if (length >= l && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }

        // Return -1 if no valid positive subarray was found
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}
