import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        
        // Step 1: Build adjacency list for the initial tree
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            adj.get(parent[i]).add(i);
        }

        // Step 2: Determine new parents simultaneously using DFS
        int[] newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1); // -1 means no ancestor with this character seen yet
        
        findNewParents(0, adj, s, lastSeen, newParent);

        // Step 3: Build adjacency list for the final tree
        List<List<Integer>> newAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newAdj.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            newAdj.get(newParent[i]).add(i);
        }

        // Step 4: Calculate subtree sizes on the final tree
        int[] answer = new int[n];
        computeSubtreeSizes(0, newAdj, answer);

        return answer;
    }

    private void findNewParents(int u, List<List<Integer>> adj, String s, int[] lastSeen, int[] newParent) {
        int charIdx = s.charAt(u) - 'a';
        int previousAncestor = lastSeen[charIdx];

        // If an ancestor with the same character exists, update newParent
        if (previousAncestor != -1) {
            newParent[u] = previousAncestor;
        }

        // Mark current node as the most recent ancestor for this character
        lastSeen[charIdx] = u;

        for (int v : adj.get(u)) {
            findNewParents(v, adj, s, lastSeen, newParent);
        }

        // Backtrack: restore the previous state for sibling branches
        lastSeen[charIdx] = previousAncestor;
    }

    private int computeSubtreeSizes(int u, List<List<Integer>> newAdj, int[] answer) {
        int size = 1; // Count the current node itself
        for (int v : newAdj.get(u)) {
            size += computeSubtreeSizes(v, newAdj, answer);
        }
        answer[u] = size;
        return size;
    }
}
