import java.util.*;

class Solution {
    public int[] arrayRankTransform(int[] arr) {

        int n = arr.length;

        if (n == 0)
            return arr;

        // Make a copy
        int[] copy = arr.clone();

        // Sort the copy
        Arrays.sort(copy);

        // Store rank of every unique element
        HashMap<Integer, Integer> map = new HashMap<>();

        int rank = 1;

        for (int num : copy) {
            if (!map.containsKey(num)) {
                map.put(num, rank);
                rank++;
            }
        }

        // Replace every element with its rank
        for (int i = 0; i < n; i++) {
            arr[i] = map.get(arr[i]);
        }

        return arr;
    }
}