
import java.util.Arrays;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        final long MOD = 1_000_000_007L;
        int n = nums.size();

        int[] bitCount = new int[31];

        // Step 1: Count how many numbers have each bit set
        for (int num : nums) {
            for (int bit = 0; bit < 31; bit++) {
                if ((num & (1 << bit)) != 0) {
                    bitCount[bit]++;
                }
            }
        }

        long answer = 0;

        // Step 2: Construct the largest possible k numbers
        for (int i = 0; i < k; i++) {
            long value = 0;

            for (int bit = 30; bit >= 0; bit--) {
                if (bitCount[bit] > 0) {
                    value |= (1L << bit);
                    bitCount[bit]--;
                }
            }

            // Step 3: Add the square modulo MOD
            answer = (answer + value * value) % MOD;
        }

        return (int) answer;
    }
}
