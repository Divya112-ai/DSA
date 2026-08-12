class Solution {
    public String countAndSay(int n) {

        String result = "1";

        for (int i = 1; i < n; i++) {

            StringBuilder next = new StringBuilder();

            int j = 0;

            while (j < result.length()) {

                char current = result.charAt(j);
                int count = 0;

                // Count consecutive same characters
                while (j < result.length() &&
                       result.charAt(j) == current) {

                    count++;
                    j++;
                }

                // Add count and character
                next.append(count);
                next.append(current);
            }

            result = next.toString();
        }

        return result;
    }
}