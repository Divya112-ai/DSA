class Solution {

    int MOD = 1000000007;
    int[][][] dp;

    public int subsequencePairCount(int[] nums) {

        int n = nums.length;

        dp = new int[n][201][201];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 200; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return dfs(0, 0, 0, nums);
    }

    private int dfs(int index, int g1, int g2, int[] nums) {

        if (index == nums.length) {
            return (g1 == g2 && g1 != 0) ? 1 : 0;
        }

        if (dp[index][g1][g2] != -1)
            return dp[index][g1][g2];

        long ans = 0;

        // Skip
        ans += dfs(index + 1, g1, g2, nums);

        // Put in first subsequence
        int ng1 = (g1 == 0) ? nums[index] : gcd(g1, nums[index]);
        ans += dfs(index + 1, ng1, g2, nums);

        // Put in second subsequence
        int ng2 = (g2 == 0) ? nums[index] : gcd(g2, nums[index]);
        ans += dfs(index + 1, g1, ng2, nums);

        dp[index][g1][g2] = (int)(ans % MOD);

        return dp[index][g1][g2];
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}