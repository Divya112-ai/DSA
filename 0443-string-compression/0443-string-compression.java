class Solution {
    public int compress(char[] chars) {

        int write = 0;
        int i = 0;

        while (i < chars.length) {

            char current = chars[i];
            int count = 0;

            // Count consecutive occurrences
            while (i < chars.length && chars[i] == current) {
                i++;
                count++;
            }

            // Write the character
            chars[write++] = current;

            // Write the count if greater than 1
            if (count > 1) {
                String str = String.valueOf(count);

                for (char ch : str.toCharArray()) {
                    chars[write++] = ch;
                }
            }
        }

        return write;
    }
}