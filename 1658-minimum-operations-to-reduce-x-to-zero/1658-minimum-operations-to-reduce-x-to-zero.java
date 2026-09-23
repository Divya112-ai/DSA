class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;

        // Calculate total sum
        long totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        // Sum of the subarray that we want to keep
        long target = totalSum - x;

        // Impossible
        if (target < 0) {
            return -1;
        }

        // Need to remove all elements
        if (target == 0) {
            return n;
        }

        int left = 0;
        long windowSum = 0;
        int maxLength = -1;

        // Sliding window
        for (int right = 0; right < n; right++) {

            windowSum += nums[right];

            // If window sum becomes greater than target,
            // shrink it from the left
            while (windowSum > target && left <= right) {
                windowSum -= nums[left];
                left++;
            }

            // Found a subarray with required sum
            if (windowSum == target) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        // No valid subarray
        if (maxLength == -1) {
            return -1;
        }

        // Remove everything outside the longest valid subarray
        return n - maxLength;
    
    }
}