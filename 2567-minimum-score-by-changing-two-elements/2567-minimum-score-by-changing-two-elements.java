class Solution {
    public int minimizeSum(int[] nums) {
         Arrays.sort(nums);
        int n = nums.length;

        // Low score can always be made 0, so score == high score (max - min)
        int changeTwoSmallest = nums[n - 1] - nums[2];
        int changeTwoLargest = nums[n - 3] - nums[0];
        int changeBothEnds = nums[n - 2] - nums[1];

        return Math.min(changeTwoSmallest, Math.min(changeTwoLargest, changeBothEnds));
    }
}