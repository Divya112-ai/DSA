import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        // Min-heap to store busy servers: {freeTime, serverId}
        PriorityQueue<int[]> busyHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        
        // TreeSet to store available server IDs
        TreeSet<Integer> availableServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            availableServers.add(i);
        }
        
        int[] requestCount = new int[k];
        int maxRequests = 0;
        
        for (int i = 0; i < arrival.length; i++) {
            int currTime = arrival[i];
            
            // Free up servers that have finished their tasks by currTime
            while (!busyHeap.isEmpty() && busyHeap.peek()[0] <= currTime) {
                int freedServer = busyHeap.poll()[1];
                availableServers.add(freedServer);
            }
            
            // If no servers are available, drop the request
            if (availableServers.isEmpty()) {
                continue;
            }
            
            // Find the preferred server starting from (i % k)
            Integer target = availableServers.ceiling(i % k);
            if (target == null) {
                // If none >= (i % k), wrap around and pick the smallest available ID >= 0
                target = availableServers.first();
            }
            
            // Assign the request to the target server
            availableServers.remove(target);
            busyHeap.offer(new int[]{currTime + load[i], target});
            requestCount[target]++;
            maxRequests = Math.max(maxRequests, requestCount[target]);
        }
        
        // Collect all servers that handled the maximum number of requests
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (requestCount[i] == maxRequests) {
                result.add(i);
            }
        }
        
        return result;
    }
}
