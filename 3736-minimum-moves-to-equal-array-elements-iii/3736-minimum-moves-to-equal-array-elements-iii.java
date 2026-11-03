class Solution {
    public int minMoves(int[] nums) {
        int maxVal = Integer.MIN_VALUE;
        // Step 1: Find the maximum element in the array
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        int totalMoves = 0;
        // Step 2: Calculate the number of moves needed to reach maxVal for each element
        for (int num : nums) {
            totalMoves += (maxVal - num);
        }
        
        return totalMoves;
    }
}