import java.util.*;

class Solution {

    static class State {
        int row, col;
        int energyLeft;
        int mask;
        int moves;

        State(int row, int col, int energyLeft, int mask, int moves) {
            this.row = row;
            this.col = col;
            this.energyLeft = energyLeft;
            this.mask = mask;
            this.moves = moves;
        }
    }

    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startRow = 0;
        int startCol = 0;

        // Store an ID for every litter cell
        int[][] litterId = new int[m][n];

        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startRow = i;
                    startCol = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        // If there is no litter
        if (litterCount == 0) {
            return 0;
        }

        int allCollected = (1 << litterCount) - 1;

        Queue<State> queue = new LinkedList<>();

        // visited[row][col][mask][energyLeft]
        boolean[][][][] visited =
                new boolean[m][n][1 << litterCount][energy + 1];

        queue.offer(new State(
                startRow,
                startCol,
                energy,
                0,
                0
        ));

        visited[startRow][startCol][0][energy] = true;

        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        while (!queue.isEmpty()) {

            State current = queue.poll();

            if (current.mask == allCollected) {
                return current.moves;
            }

            // Cannot move if energy is 0
            if (current.energyLeft == 0) {
                continue;
            }

            for (int[] dir : directions) {

                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                // Outside grid
                if (newRow < 0 || newRow >= m ||
                        newCol < 0 || newCol >= n) {
                    continue;
                }

                char cell = classroom[newRow].charAt(newCol);

                // Obstacle
                if (cell == 'X') {
                    continue;
                }

                // Moving costs 1 energy
                int newEnergy = current.energyLeft - 1;
                int newMask = current.mask;

                // Collect litter
                if (cell == 'L') {
                    int id = litterId[newRow][newCol];
                    newMask |= (1 << id);
                }

                // Reset energy
                if (cell == 'R') {
                    newEnergy = energy;
                }

                if (!visited[newRow][newCol][newMask][newEnergy]) {

                    visited[newRow][newCol][newMask][newEnergy] = true;

                    queue.offer(new State(
                            newRow,
                            newCol,
                            newEnergy,
                            newMask,
                            current.moves + 1
                    ));
                }
            }
        }

        return -1;
    }
}