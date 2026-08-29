
import java.util.*;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[target + 1];

        queue.offer(1);
        visited[1] = true;

        int moves = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int curr = queue.poll();

                if (curr == target) {
                    return moves;
                }

                // Dice can give values 1 to 6
                for (int next = curr + 1;
                     next <= Math.min(curr + 6, target);
                     next++) {

                    int[] position = getPosition(next, n);

                    int row = position[0];
                    int col = position[1];

                    int destination = next;

                    // Snake or ladder exists
                    if (board[row][col] != -1) {
                        destination = board[row][col];
                    }

                    if (!visited[destination]) {
                        visited[destination] = true;
                        queue.offer(destination);
                    }
                }
            }

            moves++;
        }

        return -1;
    }

    private int[] getPosition(int square, int n) {

        int quotient = (square - 1) / n;
        int remainder = (square - 1) % n;

        int row = n - 1 - quotient;

        int col;

        // Left to right row
        if (quotient % 2 == 0) {
            col = remainder;
        }
        // Right to left row
        else {
            col = n - 1 - remainder;
        }

        return new int[]{row, col};
    }
}