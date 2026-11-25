import java.util.*;

class Solution {
    // Two large primes and bases for Double Rolling Hash to prevent collisions
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 1315423911L % MOD1;
    private static final long BASE2 = 2654435761L % MOD2;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        
        // Precompute powers of BASE1 and BASE2
        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];
        pow1[0] = 1;
        pow2[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow1[i] = (pow1[i - 1] * BASE1) % MOD1;
            pow2[i] = (pow2[i - 1] * BASE2) % MOD2;
        }

        // Binary search on the subarray length
        int left = 1, right = n;
        int ans = n;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (hasUniqueSubarray(nums, mid, pow1, pow2)) {
                ans = mid;
                right = mid - 1; // Try to find a smaller valid length
            } else {
                left = mid + 1;  // Need a larger length
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarray(int[] nums, int len, long[] pow1, long[] pow2) {
        int n = nums.length;
        long h1 = 0, h2 = 0;

        // Compute initial hash for the first window [0 ... len - 1]
        for (int i = 0; i < len; i++) {
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;
        }

        // Track frequencies of combined 64-bit hashes
        Map<Long, Integer> freq = new HashMap<>(n - len + 1);
        long firstHash = (h1 << 32) | h2;
        freq.put(firstHash, 1);

        // Slide the window across the array
        for (int i = len; i < n; i++) {
            int outElem = nums[i - len];
            int inElem = nums[i];

            // Remove leading element and shift left
            h1 = (h1 - (outElem * pow1[len - 1]) % MOD1 + MOD1) % MOD1;
            h1 = (h1 * BASE1 + inElem) % MOD1;

            h2 = (h2 - (outElem * pow2[len - 1]) % MOD2 + MOD2) % MOD2;
            h2 = (h2 * BASE2 + inElem) % MOD2;

            long combinedHash = (h1 << 32) | h2;
            freq.put(combinedHash, freq.getOrDefault(combinedHash, 0) + 1);
        }

        // Check if any subarray occurred exactly once
        for (int count : freq.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }
}
