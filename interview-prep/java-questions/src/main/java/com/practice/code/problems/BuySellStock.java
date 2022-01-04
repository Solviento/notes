package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

// Say you have an array for which the ith element is the price of a given stock on day i.
// If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
// Note that you cannot sell a stock before you buy one.
// Input: [7,1,5,3,6,4]
// Output: 5
// Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
//              Not 7-1 = 6, as selling price needs to be larger than buying price.
public class BuySellStock implements CodeRunner {
  // Kadane's algorithm, useful for including negatives in array
  // the logic is to calculate the difference maxCur += prices ... of the original array and then find a contiguous
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

  @Override
  public void run() {
    int[] prices = {1, 3, 5, 1, 10, 2, 5, 10, 3, 4};
    System.out.println("Max profit for prices array : " + "1, 3, 5, 1, 10, 2, 5, 10, 3, 4");
    System.out.println("Using Kadane's algorithm : " + maxProfit(prices));
    System.out.println("Using basic algorithm : " + maxProfit_(prices));
  }
}