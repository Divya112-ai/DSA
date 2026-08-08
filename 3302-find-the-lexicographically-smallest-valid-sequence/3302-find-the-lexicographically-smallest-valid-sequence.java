class Solution {
    public int[] validSequence(String word1, String word2) {

        int len = word1.length();
        int len2 = word2.length();

        int[] suff = new int[len + 1];

        int j = len2 - 1;

        for (int i = len - 1; i >= 0; i--) {

            // j must be checked, not len2
            if (j >= 0 && word1.charAt(i) == word2.charAt(j)) {
                j--;
            }

            // Number of characters of word2 matched from suffix
            suff[i] = len2 - 1 - j;
        }

        int[] ans = new int[len2];

        int k = 0;
        j = 0;
        boolean used = false;

        for (int i = 0; i < len && k < len2; i++) {

            // Normal matching character
            if (j < len2 && word1.charAt(i) == word2.charAt(j)) {
                ans[k++] = i;
                j++;
            }

            // Use the one allowed different character
            else if (!used && j < len2 && suff[i + 1] >= len2 - j - 1) {
                ans[k++] = i;
                j++;
                used = true;
            }
        }

        return k == len2 ? ans : new int[0];
    }
}