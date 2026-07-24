class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        int max = 2048;

        boolean[] first = new boolean[max + 1];
        boolean[] second = new boolean[max + 1];

        // All possible XORs of two elements
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                first[nums[i] ^ nums[j]] = true;
            }
        }

        // XOR every pair XOR with every element
        for (int x = 0; x <= max; x++) {
            if (!first[x]) continue;

            for (int num : nums) {
                second[x ^ num] = true;
            }
        }

        // Count unique XOR values
        int ans = 0;
        for (int i = 0; i <= max; i++) {
            if (second[i]) ans++;
        }

        return ans;
    }
}