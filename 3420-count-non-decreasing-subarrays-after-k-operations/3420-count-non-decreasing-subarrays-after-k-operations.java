import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        
        // Deque stores pairs of [value, count] representing compressed segments
        // Maintained in strictly decreasing order of value from front to back
        Deque<int[]> dq = new ArrayDeque<>();
        
        int r = n - 1;
        for (int l = n - 1; l >= 0; l--) {
            int val = nums[l];
            int count = 1;
            
            // Merge all elements to the right that are <= nums[l]
            while (!dq.isEmpty() && dq.peekLast()[0] <= val) {
                int[] top = dq.pollLast();
                // Raising 'top[1]' elements from 'top[0]' up to 'val'
                cost += (long) (val - top[0]) * top[1];
                count += top[1];
            }
            
            // Push the newly formed dominant block
            dq.offerLast(new int[]{val, count});
            
            // Shrink the window from the right if total operations exceed k
            while (cost > k) {
                int[] front = dq.peekFirst();
                // The rightmost element's target value is front[0], and its original value is nums[r]
                cost -= (front[0] - nums[r]);
                front[1]--;
                if (front[1] == 0) {
                    dq.pollFirst();
                }
                r--;
            }
            
            // Every subarray starting at 'l' and ending between 'l' and 'r' is valid
            ans += (r - l + 1);
        }
        
        return ans;
    }
}
