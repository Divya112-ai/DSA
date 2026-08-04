class Solution {
    public int jump(int[] nums) {
         int minJumps = 0;
        int currEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            // When we reach the end of the current jump range
            if (i == currEnd) {
                minJumps++;
                currEnd = farthest;
            }
        }

        return minJumps;
    }
}