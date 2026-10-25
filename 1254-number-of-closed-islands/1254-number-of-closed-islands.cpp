class Solution {
public:
    // Change && to & so it correctly accepts the lvalue reference
    bool dfs(vector<vector<int>>& grid, int r, int c) {
        if (r < 0 || r >= grid.size() || c < 0 || c >= grid[0].size()) {
            return false;
        }
        if (grid[r][c] == 1) {
            return true;
        }
        
        grid[r][c] = 1;
        
        bool up = dfs(grid, r - 1, c);
        bool down = dfs(grid, r + 1, c); // Added the missing down move
        bool right = dfs(grid, r, c + 1);
        bool left = dfs(grid, r, c - 1);
        
        return up && down && left && right;
    }

    int closedIsland(vector<vector<int>>& grid) {
        int count = 0;
        for (int i = 0; i < grid.size(); ++i) {
            for (int j = 0; j < grid[0].size(); ++j) {
                if (grid[i][j] == 0) {
                    if (dfs(grid, i, j)) {
                        ++count;
                    }
                }
            }
        }
        return count;
    }
};
