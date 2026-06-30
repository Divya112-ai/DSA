class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int maxprofit=0;
        int buyprice=prices[0];
        for(int i=1;i<n;i++)
        {
            int currprofit=prices[i]-buyprice;
            if(currprofit>maxprofit)
            {
                maxprofit=currprofit;
            }
            if(buyprice>prices[i])
            {
                buyprice=prices[i];
            }
        }
        return maxprofit;
    }

}