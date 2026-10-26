class Solution {
    public int countIslands(int[][] grid, int k) {
      int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Found land that has not been visited
                if (grid[i][j] > 0) {
                    long sum = dfs(grid, i, j);

                    // Check whether island sum is divisible by k
                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int r, int c) {
        int m = grid.length;
        int n = grid[0].length;

        // Out of bounds or water
        if (r < 0 || r >= m || c < 0 || c >= n
                || grid[r][c] == 0) {
            return 0;
        }

        // Save the cell value
        long value = grid[r][c];

        // Mark this cell as visited
        grid[r][c] = 0;

        // Explore all 4 directions
        value += dfs(grid, r - 1, c); // Up
        value += dfs(grid, r + 1, c); // Down
        value += dfs(grid, r, c - 1); // Left
        value += dfs(grid, r, c + 1); // Right

        return value;   
    }
}