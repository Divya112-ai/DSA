import java.util.Arrays;

public class Solution {

    static class Node {
        int[] remain = new int[5]; // Stores prefix count for each remainder 0..k-1
        int prod = 1;              // Total product mod k of elements in node's range
    }

    static class SegmentTree {
        private final int n;
        private final int k;
        private final Node[] tree;

        public SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.tree = new Node[4 * n];
            for (int i = 0; i < tree.length; i++) {
                tree[i] = new Node();
            }
            build(nums, 0, 0, n - 1);
        }

        private Node merge(Node left, Node right) {
            Node res = new Node();
            res.prod = (left.prod * right.prod) % k;

            // Prefixes contained entirely within the left child
            for (int i = 0; i < k; i++) {
                res.remain[i] = left.remain[i];
            }

            // Prefixes extending into the right child
            for (int i = 0; i < k; i++) {
                int newRem = (i * left.prod) % k;
                res.remain[newRem] += right.remain[i];
            }

            return res;
        }

        private void build(int[] nums, int cur, int left, int right) {
            if (left == right) {
                int val = nums[left] % k;
                tree[cur].remain[val] = 1;
                tree[cur].prod = val;
                return;
            }
            int mid = left + (right - left) / 2;
            build(nums, 2 * cur + 1, left, mid);
            build(nums, 2 * cur + 2, mid + 1, right);
            tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
        }

        public void update(int index, int val) {
            update(0, 0, n - 1, index, val % k);
        }

        private void update(int cur, int left, int right, int index, int val) {
            if (left == right) {
                Arrays.fill(tree[cur].remain, 0);
                tree[cur].remain[val] = 1;
                tree[cur].prod = val;
                return;
            }
            int mid = left + (right - left) / 2;
            if (index <= mid) {
                update(2 * cur + 1, left, mid, index, val);
            } else {
                update(2 * cur + 2, mid + 1, right, index, val);
            }
            tree[cur] = merge(tree[2 * cur + 1], tree[2 * cur + 2]);
        }

        public Node query(int qL, int qR) {
            return query(0, 0, n - 1, qL, qR);
        }

        private Node query(int cur, int left, int right, int qL, int qR) {
            if (qL <= left && right <= qR) {
                return tree[cur];
            }
            int mid = left + (right - left) / 2;
            if (qR <= mid) {
                return query(2 * cur + 1, left, mid, qL, qR);
            }
            if (qL > mid) {
                return query(2 * cur + 2, mid + 1, right, qL, qR);
            }

            Node leftRes = query(2 * cur + 1, left, mid, qL, qR);
            Node rightRes = query(2 * cur + 2, mid + 1, right, qL, qR);
            return merge(leftRes, rightRes);
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(nums, k);
        int[] result = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // 1. Point Update (persistent)
            tree.update(index, value);

            // 2. Query range [start, n - 1] and get count for remainder x
            Node rangeAns = tree.query(start, n - 1);
            result[q] = rangeAns.remain[x];
        }

        return result;
    }
}