class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int start = nums[0];

        boolean increasing = true;
        boolean decreasing = true;

        // Check both circular patterns
        for (int i = 0; i < n; i++) {
            if (nums[i] != (start + i) % n) {
                increasing = false;
            }

            if (nums[i] != (start - i + n) % n) {
                decreasing = false;
            }
        }

        // Case 1: Increasing circular order
        if (increasing) {
            int k = (n - start) % n;

            // Rotate left k times, or rotate right
            // using reverse + left rotations + reverse
            return Math.min(k, (n - k) % n + 2 * (k == 0 ? 0 : 1));
        }

        // Case 2: Decreasing circular order
        if (decreasing) {
            // Rotate left, then reverse
            int a = (start + 1) % n;

            // Or reverse first, then rotate left
            int b = (n - a) % n;

            return Math.min(a + 1, b + 1);
        }

        // Neither pattern can be sorted
        return -1;
    }
}