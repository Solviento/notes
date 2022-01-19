/*
* You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

Input: prices = [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.

*
* */

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

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