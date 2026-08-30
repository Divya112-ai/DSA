class Solution {
    public List<String> topKFrequent(String[] words, int k) {

        HashMap<String, Integer> map = new HashMap<>();

        // Count frequency of each word
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        // Store unique words
        List<String> list = new ArrayList<>(map.keySet());

        // Sort words
        Collections.sort(list, (a, b) -> {

            // Different frequency
            if (!map.get(a).equals(map.get(b))) {
                return map.get(b) - map.get(a);
            }

            // Same frequency
            return a.compareTo(b);
        });

        return list.subList(0, k);
    }
}