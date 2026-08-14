class Solution {
    public int maximumLengthSubstring(String s) {
        int[] freq = new int[26];
        int l = 0;
        int max = 0;

        for (int right = 0; right < s.length(); right++) {
            freq[s.charAt(right) - 'a']++;

            // If current character occurs more than 2 times,
            // move left until it occurs at most 2 times
            while (freq[s.charAt(right) - 'a'] > 2) {
                freq[s.charAt(l) - 'a']--;
                l++;
            }

            // Current window is valid
            max = Math.max(max, right - l + 1);
        }

        return max;
    }
}