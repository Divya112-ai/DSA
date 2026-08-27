class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        // Frequency of characters in s
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // We will try changing target from right to left
        for (int i = n - 1; i >= 0; i--) {

            // Rebuild frequency of characters available
            // for target[0 ... i]
            int[] count = freq.clone();

            boolean possiblePrefix = true;

            // Use target's prefix before position i
            for (int j = 0; j < i; j++) {

                int x = target.charAt(j) - 'a';

                if (count[x] == 0) {
                    possiblePrefix = false;
                    break;
                }

                count[x]--;
            }

            if (!possiblePrefix) {
                continue;
            }

            // Find smallest character > target[i]
            int current = target.charAt(i) - 'a';

            for (int c = current + 1; c < 26; c++) {

                if (count[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    // 1. Same prefix as target
                    for (int j = 0; j < i; j++) {
                        ans.append(target.charAt(j));
                    }

                    // 2. Smallest character greater than target[i]
                    ans.append((char) ('a' + c));

                    count[c]--;

                    // 3. Put remaining characters in sorted order
                    for (int k = 0; k < 26; k++) {
                        while (count[k] > 0) {
                            ans.append((char) ('a' + k));
                            count[k]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}