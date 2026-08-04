class Solution {
    static final int MOD = 1_000_000_007;
    static final int MAX = 100001;

    static long[] pow10 = new long[MAX];

    static {
        pow10[0] = 1;
        for (int i = 1; i < MAX; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }
    }

    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();

        int[] sumD = new int[n + 1];
        int[] cntNonZero = new int[n + 1];
        long[] p = new long[n + 1];

        for (int i = 1; i <= n; i++) {

            int d = s.charAt(i - 1) - '0';

            sumD[i] = sumD[i - 1] + d;
            cntNonZero[i] = cntNonZero[i - 1] + (d > 0 ? 1 : 0);

            if (d == 0)
                p[i] = p[i - 1];
            else
                p[i] = (p[i - 1] * 10 + d) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            int digitSum = sumD[r + 1] - sumD[l];
            int cnt = cntNonZero[r + 1] - cntNonZero[l];

            long x = (p[r + 1] - (p[l] * pow10[cnt]) % MOD + MOD) % MOD;

            ans[i] = (int)((x * digitSum) % MOD);
        }

        return ans;
    }
}