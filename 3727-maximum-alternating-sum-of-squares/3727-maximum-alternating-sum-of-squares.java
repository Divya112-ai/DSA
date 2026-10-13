
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] arr = new long[n];

        // Step 1: Square every element
        for (int i = 0; i < n; i++) {
            arr[i] = (long) nums[i] * nums[i];
        }

        // Step 2: Sort the squared values
        Arrays.sort(arr);

        // Step 3: Add the largest half and subtract
        // the smallest half
        long ans = 0;
        int positiveCount = (n + 1) / 2;

        for (int i = 0; i < n; i++) {
            if (i < positiveCount) {
                ans += arr[n - 1 - i];
            } else {
                ans -= arr[n - 1 - i];
            }
        }

        return ans;
    }
}
