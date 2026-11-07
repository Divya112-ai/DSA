import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Free servers min-heap: sorted by [weight, index]
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Busy servers min-heap: sorted by [freeTime, weight, index]
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        // Initialize all servers as free
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[] { servers[i], i });
        }

        long currentTime = 0;

        for (int j = 0; j < m; j++) {
            // Time advances to at least j because task j arrives at second j
            currentTime = Math.max(currentTime, j);

            // Move any server that has finished its task by currentTime back to freeServers
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                int[] busy = busyServers.poll();
                freeServers.offer(new int[] { busy[1], busy[2] }); // [weight, index]
            }

            // If no free servers are available, jump time to the earliest server release time
            if (freeServers.isEmpty()) {
                currentTime = busyServers.peek()[0];
                while (!busyServers.isEmpty() && busyServers.peek()[0] <= currentTime) {
                    int[] busy = busyServers.poll();
                    freeServers.offer(new int[] { busy[1], busy[2] });
                }
            }

            // Assign the task to the top server in freeServers
            int[] server = freeServers.poll();
            int weight = server[0];
            int index = server[1];

            ans[j] = index;
            // Push the server to busyServers with its new availability time
            busyServers.offer(new int[] { (int) currentTime + tasks[j], weight, index });
        }

        return ans;
    }
}
