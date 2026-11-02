class Solution {
    public int minMoves2(int[] nums) {
          // Step 1: Sort the array to easily find the median
        Arrays.sort(nums);
        
        // Step 2: The median is the middle element
        int median = nums[nums.length / 2];
        
        int moves = 0;
        
        // Step 3: Calculate the total moves needed to make all elements equal to the median
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}