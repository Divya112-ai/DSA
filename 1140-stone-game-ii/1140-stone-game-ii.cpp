class Solution {
public:

    int n;
    vector<int> suffix;
    vector<vector<int>> dp;

    int solve(int i, int M) {

        // No stones left
        if (i >= n)
            return 0;

        // Already calculated
        if (dp[i][M] != -1)
            return dp[i][M];

        int ans = 0;

        // Try taking X piles
        for (int X = 1; X <= 2 * M && i + X <= n; X++) {

            int newM = max(M, X);

            // Current player gets:
            // total remaining - opponent's best
            int current =
                suffix[i] - solve(i + X, newM);

            ans = max(ans, current);
        }

        return dp[i][M] = ans;
    }

    int stoneGameII(vector<int>& piles) {

        n = piles.size();

        // Suffix sum
        suffix.resize(n + 1, 0);

        for (int i = n - 1; i >= 0; i--) {
            suffix[i] = piles[i] + suffix[i + 1];
        }

        // M can grow up to n
        dp.assign(n, vector<int>(n + 1, -1));

        return solve(0, 1);
    }
};