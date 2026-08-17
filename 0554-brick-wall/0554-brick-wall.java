class Solution {
    public int leastBricks(List<List<Integer>> wall) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int maxEdge = 0;

        for (List<Integer> row : wall) {

            int sum = 0;

            // Don't include the last brick
            for (int i = 0; i < row.size() - 1; i++) {

                sum += row.get(i);

                map.put(sum, map.getOrDefault(sum, 0) + 1);

                maxEdge = Math.max(maxEdge, map.get(sum));
            }
        }

        return wall.size() - maxEdge;
    }
}