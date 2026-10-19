
import java.util.*;

class Solution {
    static final long MOD = 1_000_000_007;
    long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;

        // Step 1: Precompute combinations
        comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            comb[i][i] = 1;

            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1]
                           + comb[i - 1][j]) % MOD;
            }
        }

        // Step 2: Convert array to list
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            list.add(num);
        }

        // Exclude original ordering
        return (int)((solve(list) - 1 + MOD) % MOD);
    }

    private long solve(List<Integer> nums) {

        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Step 3: Divide into left and right subtree
        for (int i = 1; i < nums.size(); i++) {
            int num = nums.get(i);

            if (num < root) {
                left.add(num);
            } else {
                right.add(num);
            }
        }

        // Step 4: Recursively calculate ways
        long leftWays = solve(left);
        long rightWays = solve(right);

        // Step 5: Calculate combinations
        int l = left.size();
        int r = right.size();

        long ways = comb[l + r][l];

        ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}