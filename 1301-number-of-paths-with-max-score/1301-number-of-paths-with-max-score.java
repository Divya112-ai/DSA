import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int MOD = 1_000_000_007;
        int n = board.size();
        
        // dpScore[i][j] stores the maximum score to reach 'E' from (i, j)
        int[][] dpScore = new int[n][n];
        // dpPaths[i][j] stores the number of paths to achieve that maximum score
        int[][] dpPaths = new int[n][n];
        
        // Base case: at the starting position 'S'
        dpPaths[n - 1][n - 1] = 1; 
        
        // Directions to check: right, down, and bottom-right diagonal (looking backward)
        int[][] dirs = {{0, 1}, {1, 0}, {1, 1}};
        
        // Traverse backwards from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Skip the start cell since it's already initialized
                if (i == n - 1 && j == n - 1) continue;
                
                // If it's an obstacle, we can't step here
                if (board.get(i).charAt(j) == 'X') continue;
                
                int maxPrevScore = -1;
                int pathsCount = 0;
                
                // Check all 3 cells we could have transitioned from
                for (int[] dir : dirs) {
                    int prevI = i + dir[0];
                    int prevJ = j + dir[1];
                    
                    // Ensure the previous cell is within bounds and reachable
                    if (prevI < n && prevJ < n && dpPaths[prevI][prevJ] > 0) {
                        if (dpScore[prevI][prevJ] > maxPrevScore) {
                            maxPrevScore = dpScore[prevI][prevJ];
                            pathsCount = dpPaths[prevI][prevJ];
                        } else if (dpScore[prevI][prevJ] == maxPrevScore) {
                            pathsCount = (pathsCount + dpPaths[prevI][prevJ]) % MOD;
                        }
                    }
                }
                
                // If this cell is reachable from at least one valid cell
                if (maxPrevScore != -1) {
                    char currChar = board.get(i).charAt(j);
                    int currVal = (currChar == 'E') ? 0 : (currChar - '0');
                    
                    dpScore[i][j] = maxPrevScore + currVal;
                    dpPaths[i][j] = pathsCount;
                }
            }
        }
        
        // Return results at destination 'E' (0, 0)
        return new int[]{dpScore[0][0], dpPaths[0][0]};
    }
}
