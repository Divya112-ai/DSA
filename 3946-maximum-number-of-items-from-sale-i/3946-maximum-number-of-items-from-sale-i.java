class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] firstValue = new int[n];
        int minPrice = Integer.MAX_VALUE;

        // 1. Precompute total items gained from the FIRST purchase of each item
        for (int i = 0; i < n; i++) {
            int count = 1; // The purchased item itself
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    count++;
                }
            }
            firstValue[i] = count;
            minPrice = Math.min(minPrice, items[i][1]);
        }

        // dp[c] = max items obtainable with cost c
        int[] dp = new int[budget + 1];

        // 2. 0-1 Knapsack: Buying the first copy of each distinct item
        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int val = firstValue[i];
            for (int c = budget; c >= price; c--) {
                dp[c] = Math.max(dp[c], dp[c - price] + val);
            }
        }

        // 3. Unbounded Knapsack: Buying additional copies (each gives +1 item)
        for (int c = minPrice; c <= budget; c++) {
            dp[c] = Math.max(dp[c], dp[c - minPrice] + 1);
        }

        // 4. Find the best result within budget
        int maxItems = 0;
        for (int c = 0; c <= budget; c++) {
            maxItems = Math.max(maxItems, dp[c]);
        }

        return maxItems;
    }
}
