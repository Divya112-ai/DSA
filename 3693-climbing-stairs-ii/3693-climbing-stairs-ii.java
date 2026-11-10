import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        // dp[i] stores the minimum cost to reach step i
        long[] dp = new long[n + 1];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            if (dp[i] == Long.MAX_VALUE) continue;

            // Try jumping to i + 1, i + 2, and i + 3
            for (int jump = 1; jump <= 3; jump++) {
                int nextStep = i + jump;
                if (nextStep <= n) {
                    // costs is 1-indexed in the problem, so we use nextStep - 1
                    int stepCost = costs[nextStep - 1];
                    long jumpCost = (long) jump * jump;
                    dp[nextStep] = Math.min(dp[nextStep], dp[i] + stepCost + jumpCost);
                }
            }
        }

        return (int) dp[n];
    }
}
