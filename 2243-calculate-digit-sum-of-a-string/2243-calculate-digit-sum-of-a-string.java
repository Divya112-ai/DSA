class Solution {
    public String digitSum(String s, int k) {
        // Continue rounds while the string length is greater than k
        while (s.length() > k) {
            StringBuilder sb = new StringBuilder();
            
            // Process the string in chunks of size k
            for (int i = 0; i < s.length(); i += k) {
                int sum = 0;
                
                // Sum digits in the current group, stopping at s.length() for the last group
                for (int j = i; j < i + k && j < s.length(); j++) {
                    sum += s.charAt(j) - '0';
                }
                
                // Append the sum of the current group to the new string
                sb.append(sum);
            }
            
            // Update s for the next round
            s = sb.toString();
        }
        
        return s;
    }
}
