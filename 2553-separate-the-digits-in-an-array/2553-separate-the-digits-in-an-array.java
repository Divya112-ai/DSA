import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> digitsList = new ArrayList<>();
        
        // Traverse the array backwards to avoid explicit reversal steps
        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];
            
            // Extract digits from right to left
            while (num > 0) {
                digitsList.add(num % 10);
                num /= 10;
            }
        }
        
        // Create the result array
        int[] result = new int[digitsList.size()];
        int index = 0;
        
        // Populate the result array in reverse order to correct the sequence
        for (int i = digitsList.size() - 1; i >= 0; i--) {
            result[index++] = digitsList.get(i);
        }
        
        return result;
    }
}
