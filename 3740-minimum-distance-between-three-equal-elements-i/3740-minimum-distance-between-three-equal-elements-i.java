class Solution {
    public int minimumDistance(int[] nums) {

        int ans = Integer.MAX_VALUE;

        // Store positions of each number
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        // Check every group
        for (List<Integer> list : map.values()) {

            // Need at least 3 occurrences
            for (int i = 0; i + 2 < list.size(); i++) {

                int first = list.get(i);
                int third = list.get(i + 2);

                int distance = 2 * (third - first);

                ans = Math.min(ans, distance);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}