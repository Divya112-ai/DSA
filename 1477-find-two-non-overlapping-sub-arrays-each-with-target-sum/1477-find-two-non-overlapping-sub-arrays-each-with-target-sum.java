class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] bestFromIndex = new int[n]; 
        // bestFromIndex[i] stores min length of sub-array with sum=target starting at index >= i
        // Or store min length ending at <= i. Let's use ending at <= i.
        int[] minEnding = new int[n];
        java.util.Arrays.fill(minEnding, Integer.MAX_VALUE);
        
        int sum = 0, left = 0, minLen = Integer.MAX_VALUE, res = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            while (sum > target) {
                sum -= arr[left];
                left++;
            }
            if (sum == target) {
                int curLen = i - left + 1;
                // Check if there is a valid non-overlapping sub-array before 'left'
                if (left > 0 && minEnding[left - 1] != Integer.MAX_VALUE) {
                    res = Math.min(res, curLen + minEnding[left - 1]);
                }
                minLen = Math.min(minLen, curLen);
            }
            minEnding[i] = minLen;
        }
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
