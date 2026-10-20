
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);

        int answer = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean sortable = true;

            for (int start = 0; start < n; start += k) {
                int end = start + k;
                int descent = -1;

                for (int j = start + 1; j < end; j++) {
                    if (nums[j] < nums[j - 1]) {
                        if (descent != -1) {
                            sortable = false;
                            break;
                        }
                        descent = j;
                    }
                }

                if (!sortable) {
                    break;
                }

                if (descent != -1 && nums[end - 1] > nums[start]) {
                    sortable = false;
                    break;
                }

                for (int j = 0; j < k; j++) {
                    int index;

                    if (descent == -1) {
                        index = start + j;
                    } else {
                        index = start + (descent - start + j) % k;
                    }

                    if (nums[index] != sorted[start + j]) {
                        sortable = false;
                        break;
                    }
                }

                if (!sortable) {
                    break;
                }
            }

            if (sortable) {
                answer += k;
            }
        }

        return answer;
    }
}
