
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Case 1: Already sorted
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            return 0;
        }

        // A proper substring of a length-2 string
        // cannot change its order.
        if (n == 2) {
            return -1;
        }

        char minChar = 'z';
        char maxChar = 'a';
        int[] freq = new int[26];

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            minChar = (char) Math.min(minChar, c);
            maxChar = (char) Math.max(maxChar, c);

            freq[c - 'a']++;
        }

        char first = s.charAt(0);
        char last = s.charAt(n - 1);

        // Case 2: One endpoint is already correct
        if (first == minChar || last == maxChar) {
            return 1;
        }

        // Case 3: Both extreme characters are at the ends
        // and each occurs exactly once.
        if (first == maxChar && last == minChar
                && freq[maxChar - 'a'] == 1
                && freq[minChar - 'a'] == 1) {
            return 3;
        }

        // Case 4: All other unsorted strings
        return 2;
    }
}
