import java.util.*;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        // Store key -> value
        Map<String, String> map = new HashMap<>();

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();

        int i = 0;

        while (i < s.length()) {

            // Normal character
            if (s.charAt(i) != '(') {
                result.append(s.charAt(i));
                i++;
            } 
            else {
                // Find the closing bracket
                int j = i + 1;

                while (s.charAt(j) != ')') {
                    j++;
                }

                // Extract key
                String key = s.substring(i + 1, j);

                // Get value, or ? if key doesn't exist
                if (map.containsKey(key)) {
                    result.append(map.get(key));
                } else {
                    result.append('?');
                }

                // Move after ')'
                i = j + 1;
            }
        }

        return result.toString();
    }
}
