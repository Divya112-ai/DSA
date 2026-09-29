class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Total path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        if (grid[0][0] == ')') {
            return false;
        }

        boolean[][][] dp = new boolean[m][n][m + n];

        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                int change = grid[i][j] == '(' ? 1 : -1;

                for (int balance = 0; balance < m + n; balance++) {

                    int prev = balance - change;

                    if (prev < 0 || prev >= m + n) {
                        continue;
                    }

                    if (i > 0 && dp[i - 1][j][prev]) {
                        dp[i][j][balance] = true;
                    }

                    if (j > 0 && dp[i][j - 1][prev]) {
                        dp[i][j][balance] = true;
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}