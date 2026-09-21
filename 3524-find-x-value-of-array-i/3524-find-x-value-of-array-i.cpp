class Solution {
public:
    vector<long long> resultArray(vector<int>& nums, int k) {
        vector<long long> ans(k, 0);
        vector<long long> dp(k, 0);

        for (int num : nums) {
            int val = num % k;

            vector<long long> next(k, 0);

            // Subarray containing only nums[i]
            next[val]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {
                int newRemainder = (r * val) % k;
                next[newRemainder] += dp[r];
            }

            // Add all subarrays ending at current index
            for (int r = 0; r < k; r++) {
                ans[r] += next[r];
            }

            dp = next;
        }

        return ans; 
    }
};