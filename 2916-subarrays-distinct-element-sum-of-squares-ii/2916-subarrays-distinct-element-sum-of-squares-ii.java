
class Solution {
    static final long MOD = 1_000_000_007L;

    int[] last;
    long[] sum, sq, lazy;
    int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];
        last = new int[100001];

        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int r = 0; r < n; r++) {
            int x = nums[r];

            // Increase distinct counts for affected subarrays
            update(1, 0, n - 1, last[x] + 1, r);

            // Sum of squared distinct counts of subarrays
            // ending at index r
            ans = (ans + sq[1]) % MOD;

            last[x] = r;
        }

        return (int) ans;
    }

    void push(int node, int l, int r) {
        if (lazy[node] == 0 || l == r) return;

        int mid = (l + r) / 2;
        int left = node * 2;
        int right = left + 1;
        long v = lazy[node];

        apply(left, mid - l + 1, v);
        apply(right, r - mid, v);

        lazy[node] = 0;
    }

    void apply(int node, int len, long v) {
        sq[node] = (sq[node] + 2 * v * sum[node]
                    + v * v % MOD * len) % MOD;

        sum[node] = (sum[node] + v * len) % MOD;
        lazy[node] = (lazy[node] + v) % MOD;
    }

    void update(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l || ql > qr) return;

        if (ql <= l && r <= qr) {
            apply(node, r - l + 1, 1);
            return;
        }

        push(node, l, r);

        int mid = (l + r) / 2;

        update(node * 2, l, mid, ql, qr);
        update(node * 2 + 1, mid + 1, r, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}
