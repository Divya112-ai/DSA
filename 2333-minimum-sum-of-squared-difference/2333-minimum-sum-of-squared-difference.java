
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2,
                                 int k1, int k2) {

        int maxDiff = 100000;
        long[] freq = new long[maxDiff + 1];

        // Step 1: Count each absolute difference
        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
        }

        long k = (long) k1 + k2;

        // Step 2: Reduce larger differences first
        for (int d = maxDiff; d > 0 && k > 0; d--) {
            long reduce = Math.min(freq[d], k);

            freq[d] -= reduce;
            freq[d - 1] += reduce;
            k -= reduce;
        }

        // Step 3: Calculate the minimum sum of squares
        long ans = 0;

        for (int d = 1; d <= maxDiff; d++) {
            ans += freq[d] * d * d;
        }

        return ans;
    }
}
