class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        // Cast to long to prevent integer overflow during multiplication and addition
        long c1 = cost1;
        long c2 = cost2;
        long cb = costBoth;
        long n1 = need1;
        long n2 = need2;

        // 1. Find the cheapest way to satisfy 1 unit of BOTH needs simultaneously
        long minPairCost = Math.min(c1 + c2, cb);
        long pairedUnits = Math.min(n1, n2);
        long totalCost = pairedUnits * minPairCost;

        // 2. Satisfy any remaining type 1 needs (buying type 3 might still be cheaper than type 1)
        if (n1 > pairedUnits) {
            long remaining1 = n1 - pairedUnits;
            totalCost += remaining1 * Math.min(c1, cb);
        }

        // 3. Satisfy any remaining type 2 needs (buying type 3 might still be cheaper than type 2)
        if (n2 > pairedUnits) {
            long remaining2 = n2 - pairedUnits;
            totalCost += remaining2 * Math.min(c2, cb);
        }

        return totalCost;
    }
}
