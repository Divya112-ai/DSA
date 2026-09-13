class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;

        // Shift img1 by rowShift and colShift
        for (int rowShift = -(n - 1); rowShift <= n - 1; rowShift++) {
            for (int colShift = -(n - 1); colShift <= n - 1; colShift++) {

                int overlap = 0;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {

                        // Position in img1 after shifting
                        int x = i + rowShift;
                        int y = j + colShift;

                        // Check if shifted position is inside the matrix
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            if (img1[i][j] == 1 && img2[x][y] == 1) {
                                overlap++;
                            }
                        }
                    }
                }

                maxOverlap = Math.max(maxOverlap, overlap);
            }
        }

        return maxOverlap;
    }
}