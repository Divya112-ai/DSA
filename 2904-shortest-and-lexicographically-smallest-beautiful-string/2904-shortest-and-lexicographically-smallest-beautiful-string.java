class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        // Store positions of all 1s
        int n = s.length();
        int[] ones = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones[count++] = i;
            }
        }

        // Not enough 1s
        if (count < k) {
            return "";
        }

        int minLength = Integer.MAX_VALUE;
        String answer = "";

        // Consider every group of k consecutive 1s
        for (int i = 0; i + k - 1 < count; i++) {

            int start = ones[i];
            int end = ones[i + k - 1];

            int length = end - start + 1;

            String current = s.substring(start, end + 1);

            // First find minimum length.
            // If same length, choose lexicographically smaller.
            if (length < minLength ||
                (length == minLength && current.compareTo(answer) < 0)) {

                minLength = length;
                answer = current;
            }
        }

        return answer;
    }
}