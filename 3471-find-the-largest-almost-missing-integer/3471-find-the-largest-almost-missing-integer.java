class Solution {

    public int largestInteger(int[] nums, int k) {

        int res = -1;
        int len = nums.length;

        // Case 1: only one subarray exists
        if (len == k) {
            for (int x : nums) {
                res = Math.max(res, x);
            }
            return res;
        }

        // Map: number -> number of subarrays of size k
        // in which the number appears
        Map<Integer, Integer> map = new HashMap<>();

        // Generate every subarray of size k
        for (int i = 0; i <= len - k; i++) {

            Set<Integer> set = new HashSet<>();

            for (int j = i; j < i + k; j++) {
                set.add(nums[j]);
            }

            // Each number should be counted only once
            // for this particular subarray
            for (int x : set) {
                map.put(x, map.getOrDefault(x, 0) + 1);
            }
        }

        // Find largest number appearing in exactly one subarray
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            if (entry.getValue() == 1) {
                res = Math.max(res, entry.getKey());
            }
        }

        return res;
    }
}