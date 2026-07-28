class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) {
            return false;
        }

        // Array to count frequencies of characters (a-z)
        int[] counts = new int[26];

        // Increment counts for each character in the magazine
        for (char c : magazine.toCharArray()) {
            counts[c - 'a']++;
        }

        // Decrement counts for each character in the ransom note
        for (char c : ransomNote.toCharArray()) {
            counts[c - 'a']--;
            // If the count drops below 0, magazine didn't have enough of this letter
            if (counts[c - 'a'] < 0) {
                return false;
            }
        }

        return true;
    

    }
}