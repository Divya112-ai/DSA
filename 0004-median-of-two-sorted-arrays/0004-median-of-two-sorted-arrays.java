class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search on the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m;

        int half = (m + n + 1) / 2;

        while (low <= high) {

            // Partition nums1
            int i = (low + high) / 2;

            // Partition nums2
            int j = half - i;

            // Left and right values of nums1
            int leftA = (i == 0)
                    ? Integer.MIN_VALUE
                    : nums1[i - 1];

            int rightA = (i == m)
                    ? Integer.MAX_VALUE
                    : nums1[i];

            // Left and right values of nums2
            int leftB = (j == 0)
                    ? Integer.MIN_VALUE
                    : nums2[j - 1];

            int rightB = (j == n)
                    ? Integer.MAX_VALUE
                    : nums2[j];

            // Correct partition
            if (leftA <= rightB && leftB <= rightA) {

                // Total length is even
                if ((m + n) % 2 == 0) {

                    int leftMax = Math.max(leftA, leftB);
                    int rightMin = Math.min(rightA, rightB);

                    return (leftMax + rightMin) / 2.0;
                }

                // Total length is odd
                else {
                    return Math.max(leftA, leftB);
                }
            }

            // We took too many elements from nums1
            else if (leftA > rightB) {
                high = i - 1;
            }

            // We took too few elements from nums1
            else {
                low = i + 1;
            }
        }

        return 0.0;
    }
}