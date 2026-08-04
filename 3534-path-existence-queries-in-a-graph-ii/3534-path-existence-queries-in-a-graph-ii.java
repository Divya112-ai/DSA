import java.util.*;

class Solution {

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        // (value, originalIndex)
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        // Sort according to value
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // position of every original index in sorted order
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[arr[i][1]] = i;
        }

        // next[i] = farthest index reachable in one edge
        int[] next = new int[n];
        int r = 0;

        for (int l = 0; l < n; l++) {

            while (r + 1 < n && arr[r + 1][0] - arr[l][0] <= maxDiff) {
                r++;
            }

            next[l] = r;

            if (r == l)
                r++;
        }

        int LOG = 18;        // because 2^17 > 100000

        int[][] jump = new int[LOG][n];

        for (int i = 0; i < n; i++) {
            jump[0][i] = next[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                jump[k][i] = jump[k - 1][jump[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int u = pos[queries[q][0]];
            int v = pos[queries[q][1]];

            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }

            if (u == v) {
                ans[q] = 0;
                continue;
            }

            int steps = 0;
            int cur = u;

            for (int k = LOG - 1; k >= 0; k--) {

                if (jump[k][cur] < v) {
                    cur = jump[k][cur];
                    steps += (1 << k);
                }
            }

            if (jump[0][cur] >= v)
                ans[q] = steps + 1;
            else
                ans[q] = -1;
        }

        return ans;
    }
}