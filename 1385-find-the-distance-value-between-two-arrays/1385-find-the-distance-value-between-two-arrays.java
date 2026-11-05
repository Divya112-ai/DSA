import java.util.Arrays;

public class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Sort arr2 to enable binary search
        Arrays.sort(arr2);
        int distanceValue = 0;

        for (int val : arr1) {
            // Check if any element in arr2 falls within [val - d, val + d]
            if (!hasElementInRange(arr2, val - d, val + d)) {
                distanceValue++;
            }
        }

        return distanceValue;
    }

    private boolean hasElementInRange(int[] arr, int min, int max) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] >= min && arr[mid] <= max) {
                return true; // Found an element within the invalid range
            } else if (arr[mid] < min) {
                left = mid + 1; // Search the right half
            } else {
                right = mid - 1; // Search the left half
            }
        }
        return false;
    }
}
