class Solution {
    int[][] dir={{0,1},{1,0},{-1,0},{0,-1}};
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m=grid.size(),n=grid.get(0).size();
        int[][] arr=grid.stream().map(inner-> inner.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new); 
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->Integer.compare(a[0],b[0]));
        pq.offer(new int[]{arr[0][0],0,0});
        arr[0][0]=Integer.MAX_VALUE;
        while(!pq.isEmpty())
        {
            int curr[]=pq.remove();
            int cost=curr[0],x=curr[1],y=curr[2];
            if(x==m-1&&y==n-1)
                return true;   
            for(int d[]:dir)
            {
                int r=x+d[0];
                int c=y+d[1];
                if(r<0||r>=m||c<0||c>=n||health-arr[r][c]<=0)
                    continue;
                int nextcost=cost+arr[r][c];
                if(nextcost<health)
                {
                    pq.offer(new int[]{nextcost,r,c});
                }
                arr[r][c]=Integer.MAX_VALUE;
            }  
           
        }
         return false;   
    }
}