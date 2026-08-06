class Solution {
    public int findKthPositive(int[] arr, int k) {
        for(int i=0;i<arr.length;i++)
        {
            int missing=arr[i]-(i+1);
            if(missing>=k)
            {
                return i+k;
            }
        }
        int totalmissing=arr[arr.length-1]-arr.length;
        int remaining=k-totalmissing;
        return arr[arr.length-1] + remaining;
    }
}