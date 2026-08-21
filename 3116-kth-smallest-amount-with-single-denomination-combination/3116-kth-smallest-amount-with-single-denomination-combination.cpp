class Solution {
public:
    long long findKthSmallest(vector<int>& coins, int k) {
        int n = coins.size();

        // The kth multiple of the smallest coin is always
        // an upper bound for the answer.
        long long lo = 1;
        long long hi = 1LL * (*min_element(coins.begin(), coins.end())) * k;

        // Count how many valid amounts are <= x.
        auto countValid = [&](long long x) -> long long {
            long long count = 0;

            // Inclusion-exclusion over all subsets of coins.
            for (int mask = 1; mask < (1 << n); mask++) {
                long long l = 1;
                int bits = 0;
                bool valid = true;

                for (int i = 0; i < n; i++) {
                    if (mask & (1 << i)) {
                        bits++;

                        long long g = std::gcd(l, (long long)coins[i]);
                        l = (l / g) * coins[i];

                        // No multiple of this LCM can be <= x.
                        if (l > x) {
                            valid = false;
                            break;
                        }
                    }
                }

                if (!valid)
                    continue;

                if (bits % 2 == 1)
                    count += x / l;
                else
                    count -= x / l;
            }

            return count;
        };

        // Binary search for the smallest x
        // such that at least k valid amounts are <= x.
        while (lo < hi) {
            long long mid = lo + (hi - lo) / 2;

            if (countValid(mid) >= k)
                hi = mid;
            else
                lo = mid + 1;
        }

        return lo;
    }
};