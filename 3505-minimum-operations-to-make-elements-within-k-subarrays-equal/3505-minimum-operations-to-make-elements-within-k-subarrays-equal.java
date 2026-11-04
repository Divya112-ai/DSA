import java.util.*;

public class Solution {
    private int m;
    private int[] bitCount;
    private long[] bitSum;

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Step 1: Coordinate Compression
        int[] unique = nums.clone();
        Arrays.sort(unique);
        int uniqueLen = 0;
        for (int i = 0; i < n; i++) {
            if (uniqueLen == 0 || unique[i] != unique[uniqueLen - 1]) {
                unique[uniqueLen++] = unique[i];
            }
        }
        unique = Arrays.copyOf(unique, uniqueLen);
        m = uniqueLen;

        // Step 2: Initialize Fenwick Trees
        bitCount = new int[m + 1];
        bitSum = new long[m + 1];

        long[] C = new long[n]; 
        long totalWindowSum = 0;

        for (int i = 0; i < n; i++) {
            int rank = Arrays.binarySearch(unique, nums[i]);
            add(rank, nums[i], 1);
            totalWindowSum += nums[i];

            if (i >= x) {
                int prevRank = Arrays.binarySearch(unique, nums[i - x]);
                add(prevRank, nums[i - x], -1);
                totalWindowSum -= nums[i - x];
            }

            if (i >= x - 1) {
                int targetKth = (x + 1) / 2;
                int medRank = findKth(targetKth);
                long medVal = unique[medRank];

                long[] queryResult = query(medRank);
                long countLE = queryResult[0];
                long sumLE = queryResult[1];

                long countG = x - countLE;
                long sumG = totalWindowSum - sumLE;

                long cost = (countLE * medVal - sumLE) + (sumG - countG * medVal);
                C[i] = cost;
            }
        }

        // Step 3: Dynamic Programming
        long[][] dp = new long[n + 1][k + 1];
        long INF = Long.MAX_VALUE / 2; 
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0; 
        }

        for (int j = 1; j <= k; j++) {
            for (int i = x; i <= n; i++) {
                dp[i][j] = dp[i - 1][j];
                if (dp[i - x][j - 1] != INF) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - x][j - 1] + C[i - 1]);
                }
            }
        }

        return dp[n][k];
    }

    private void add(int rank, int val, int delta) {
        int idx = rank + 1;
        while (idx <= m) {
            bitCount[idx] += delta;
            bitSum[idx] += (long) delta * val;
            idx += idx & (-idx);
        }
    }

    private int findKth(int kth) {
        int idx = 0;
        int currentSum = 0;
        for (int i = 17; i >= 0; i--) {
            int nextIdx = idx + (1 << i);
            if (nextIdx <= m && currentSum + bitCount[nextIdx] < kth) {
                idx = nextIdx;
                currentSum += bitCount[idx];
            }
        }
        return idx; 
    }

    private long[] query(int rank) {
        int idx = rank + 1;
        long count = 0;
        long sum = 0;
        while (idx > 0) {
            count += bitCount[idx];
            sum += bitSum[idx];
            idx -= idx & (-idx);
        }
        return new long[]{count, sum};
    }
}
