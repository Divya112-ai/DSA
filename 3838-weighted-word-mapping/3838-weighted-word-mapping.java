class Solution {
    public String mapWordWeights(String[] words, int[] weights) {

        StringBuilder ans = new StringBuilder();

        for (String word : words) {

            int sum = 0;

            for (char ch : word.toCharArray()) {
                sum += weights[ch - 'a'];
            }

            sum = sum % 26;

            char c = (char) ('z' - sum);

            ans.append(c);
        }

        return ans.toString();
    }
}