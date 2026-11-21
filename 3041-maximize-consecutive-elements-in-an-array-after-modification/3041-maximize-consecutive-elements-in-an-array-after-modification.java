import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int num : nums) {
            int asNext = dp.getOrDefault(num, 0) + 1;
            int asCurrent = dp.getOrDefault(num - 1, 0) + 1;

            dp.put(num + 1, asNext);
            dp.put(num, asCurrent);

            ans = Math.max(ans, Math.max(asCurrent, asNext));
        }

        return ans;
    }
}
