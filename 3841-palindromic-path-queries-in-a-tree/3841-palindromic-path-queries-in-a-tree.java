
import java.util.*;

class Solution {
    private int LOG;
    private List<Integer>[] adj;
    private int[][] up;
    private int[] depth, tin, tout, baseMask;
    private Fenwick bit;

    private static class Fenwick {
        int n;
        int[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 1];
        }

        void update(int index, int value) {
            index++;
            while (index <= n) {
                tree[index] ^= value;
                index += index & -index;
            }
        }

        int query(int index) {
            index++;
            int result = 0;
            while (index > 0) {
                result ^= tree[index];
                index -= index & -index;
            }
            return result;
        }

        void rangeUpdate(int left, int right, int value) {
            update(left, value);
            if (right + 1 < n) {
                update(right + 1, value);
            }
        }
    }

    private int mask(char c) {
        return 1 << (c - 'a');
    }

    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        baseMask = new int[n];

        char[] chars = s.toCharArray();
        int[] parent = new int[n];
        int[] order = new int[n];
        int[] stack = new int[n];
        boolean[] visited = new boolean[n];

        int top = 0, count = 0;
        stack[top++] = 0;
        visited[0] = true;

        while (top > 0) {
            int u = stack[--top];

            tin[u] = count;
            order[count++] = u;

            baseMask[u] = (u == 0 ? 0 : baseMask[parent[u]])
                    ^ mask(chars[u]);

            for (int v : adj[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    parent[v] = u;
                    depth[v] = depth[u] + 1;
                    stack[top++] = v;
                }
            }
        }

        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[parent[u]] += size[u];
        }

        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + size[u] - 1;
        }

        for (int u = 0; u < n; u++) {
            up[0][u] = parent[u];
        }

        for (int j = 1; j < LOG; j++) {
            for (int u = 0; u < n; u++) {
                up[j][u] = up[j - 1][up[j - 1][u]];
            }
        }

        bit = new Fenwick(n);
        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");

            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);

                int delta = mask(chars[u]) ^ mask(c);
                bit.rangeUpdate(tin[u], tout[u], delta);
                chars[u] = c;

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int w = lca(u, v);

                int maskU = baseMask[u] ^ bit.query(tin[u]);
                int maskV = baseMask[v] ^ bit.query(tin[v]);

                int pathMask = maskU ^ maskV ^ mask(chars[w]);

                answer.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        return answer;
    }

    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }
}
