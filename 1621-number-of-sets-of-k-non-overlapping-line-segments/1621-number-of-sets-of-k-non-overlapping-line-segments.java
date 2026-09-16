class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007; // Underscores are valid digit separators in Java
        
        int totalN = n + k - 1;
        int totalR = 2 * k;
        
        if (totalR > totalN) {
            return 0;
        }
        
        int[][] dp = new int[totalN + 1][totalR + 1];
        
        for (int i = 0; i <= totalN; i++) {
            dp[i][0] = 1; 
            for (int j = 1; j <= Math.min(i, totalR); j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % MOD;
            }
        }
        
        return dp[totalN][totalR];
    }
}
