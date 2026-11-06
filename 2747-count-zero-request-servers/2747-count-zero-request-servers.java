import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i; // store original index
        }

        // Sort queries in ascending order of time
        Arrays.sort(sortedQueries, Comparator.comparingInt(a -> a[0]));

        // Sort logs in ascending order of time
        Arrays.sort(logs, Comparator.comparingInt(a -> a[1]));

        int[] ans = new int[m];
        Map<Integer, Integer> serverCount = new HashMap<>();
        
        int l = 0, r = 0;
        int logsLen = logs.length;

        for (int[] q : sortedQueries) {
            int queryTime = q[0];
            int originalIdx = q[1];
            int leftWindow = queryTime - x;
            int rightWindow = queryTime;

            // Expand right pointer to include logs <= rightWindow
            while (r < logsLen && logs[r][1] <= rightWindow) {
                serverCount.put(logs[r][0], serverCount.getOrDefault(logs[r][0], 0) + 1);
                r++;
            }

            // Shrink left pointer to remove logs < leftWindow
            while (l < logsLen && logs[l][1] < leftWindow) {
                int serverId = logs[l][0];
                serverCount.put(serverId, serverCount.get(serverId) - 1);
                if (serverCount.get(serverId) == 0) {
                    serverCount.remove(serverId);
                }
                l++;
            }

            // Total servers - active servers with at least one request
            ans[originalIdx] = n - serverCount.size();
        }

        return ans;
    }
}
