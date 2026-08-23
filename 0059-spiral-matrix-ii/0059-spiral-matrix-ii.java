class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        int num = 1;

        while (top <= bottom && left <= right) {

            // 1. Left -> Right
            for (int j = left; j <= right; j++) {
                matrix[top][j] = num++;
            }
            top++;

            // 2. Top -> Bottom
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--;

            // 3. Right -> Left
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    matrix[bottom][j] = num++;
                }
                bottom--;
            }

            // 4. Bottom -> Top
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = num++;
                }
                left++;
            }
        }

        return matrix;
    }
}