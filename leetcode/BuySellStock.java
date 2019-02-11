class BuySellStock {
  // Kadane's algorithm, useful for includin negatives in array
  // the logic is to calcualte the difference maxCur += prices ... of the original array and then find a contiguous
  // sub array giving max profit, if difference falls below zero, reset to zero
  public int maxProfit(int[] prices) {
    int currentMax = 0;
    int maxSoFar = 0;
    for (int i = 1; i < prices.length; i++) {
      currentMax = Math.max(0, currentMax += prices[i] - prices[i - 1]);
      maxSoFar = Math.max(currentMax, maxSoFar);
    }
    return maxSoFar;
  }
  // plot array onto a graph
  // must find smallest valley and highest peak
  public int maxProfit_(int prices[]) {
    int minprice = Integer.MAX_VALUE;
    int maxprofit = 0;
    for (int i = 0; i < prices.length; i++) {
      if (prices[i] < minprice)
        minprice = prices[i];
      // highest peak found + valley found
      else if (prices[i] - minprice > maxprofit)
        maxprofit = prices[i] - minprice;
    }
    return maxprofit;
  }
}