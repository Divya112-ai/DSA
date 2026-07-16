import java.util.Arrays;

class Solution {
    public long gcdSum(int[] nums) {
        int max = -1;
        int n = nums.length;
        int[] prefixGCD = new int[n];

        for (int i = 0; i < n; i++) {
            max = Math.max(nums[i], max);
            prefixGCD[i] = gcd(nums[i], max);
        }

        long ans = 0;    // Changed from int to long

        Arrays.sort(prefixGCD);

        for (int i = 0, j = n - 1; i < j; i++, j--) {
            ans += gcd(prefixGCD[i], prefixGCD[j]);
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}