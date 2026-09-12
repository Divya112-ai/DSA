class Solution {
public:
    vector<int> maximumWeight(vector<vector<int>>& A) {

        int n = A.size();

        for (int i = 0; i < n; i++) {
            A[i].push_back(i);
        }

        sort(A.begin(), A.end(), [](const vector<int>& a,
                                    const vector<int>& b) {
            return a[1] < b[1];
        });

        vector<vector<long long>> dp(
            n + 1, vector<long long>(5, 0)
        );

        vector<vector<vector<int>>> indices(
            n + 1, vector<vector<int>>(5)
        );

        for (int i = 0; i < n; ++i) {

            int l = A[i][0];
            int r = A[i][1];
            int w = A[i][2];
            int idx = A[i][3];

            int k = lower_bound(
                A.begin(),
                A.begin() + i,
                l,
                [](const vector<int>& t, int val) {
                    return t[1] < val;
                }
            ) - A.begin();

            for (int j = 1; j < 5; ++j) {

                long long s1 = dp[i][j];
                long long s2 = dp[k][j - 1] + w;

                if (s1 > s2) {
                    dp[i + 1][j] = dp[i][j];
                    indices[i + 1][j] = indices[i][j];
                    continue;
                }

                vector<int> newIndex = indices[k][j - 1];
                newIndex.push_back(idx);

                sort(newIndex.begin(), newIndex.end());

                if (s1 == s2 &&
                    indices[i][j] < newIndex) {
                    newIndex = indices[i][j];
                }

                dp[i + 1][j] = s2;
                indices[i + 1][j] = newIndex;
            }
        }

        return indices[n][4];
    }
};