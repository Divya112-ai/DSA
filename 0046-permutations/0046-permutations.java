class Solution {

    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean[] used = new boolean[nums.length];

        backtrack(nums, used, new ArrayList<>());

        return ans;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> current) {

        // If current permutation contains all numbers
        if (current.size() == nums.length) {
            ans.add(new ArrayList<>(current));
            return;
        }

        // Try every number
        for (int i = 0; i < nums.length; i++) {

            // Already used
            if (used[i]) {
                continue;
            }

            // Choose nums[i]
            used[i] = true;
            current.add(nums[i]);

            // Recursively build the rest
            backtrack(nums, used, current);

            // Undo the choice (BACKTRACK)
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}