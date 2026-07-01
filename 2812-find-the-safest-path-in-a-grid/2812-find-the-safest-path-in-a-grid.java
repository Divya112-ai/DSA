class Solution {

    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {

        int n = grid.size();

        // Step 1: Find distance of every cell from nearest thief
        int[][] distance = calculateDistance(grid);

        // Step 2: Binary Search on answer
        int low = 0;
        int high = 2 * n;
        int answer = 0;

        while(low <= high){

            int mid = low + (high - low)/2;

            if(canReach(distance, mid)){

                answer = mid;
                low = mid + 1;

            }else{

                high = mid - 1;
            }
        }

        return answer;
    }

    // Multi-source BFS
    private int[][] calculateDistance(List<List<Integer>> grid){

        int n = grid.size();

        int[][] dist = new int[n][n];

        for(int[] row : dist)
            Arrays.fill(row,-1);

        Queue<int[]> queue = new LinkedList<>();

        // Put every thief into queue
        for(int i=0;i<n;i++){

            for(int j=0;j<n;j++){

                if(grid.get(i).get(j)==1){

                    dist[i][j]=0;
                    queue.offer(new int[]{i,j});
                }
            }
        }

        while(!queue.isEmpty()){

            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];

            for(int[] dir : directions){

                int nx = x + dir[0];
                int ny = y + dir[1];

                if(nx>=0 && ny>=0 && nx<n && ny<n
                        && dist[nx][ny]==-1){

                    dist[nx][ny]=dist[x][y]+1;

                    queue.offer(new int[]{nx,ny});
                }
            }
        }

        return dist;
    }

    // BFS to check if path exists
    private boolean canReach(int[][] dist,int safety){

        int n = dist.length;

        if(dist[0][0]<safety)
            return false;

        boolean[][] visited = new boolean[n][n];

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0,0});
        visited[0][0]=true;

        while(!queue.isEmpty()){

            int[] current=queue.poll();

            int x=current[0];
            int y=current[1];

            if(x==n-1 && y==n-1)
                return true;

            for(int[] dir:directions){

                int nx=x+dir[0];
                int ny=y+dir[1];

                if(nx>=0 && ny>=0 && nx<n && ny<n
                        && !visited[nx][ny]
                        && dist[nx][ny]>=safety){

                    visited[nx][ny]=true;

                    queue.offer(new int[]{nx,ny});
                }
            }
        }

        return false;
    }
}