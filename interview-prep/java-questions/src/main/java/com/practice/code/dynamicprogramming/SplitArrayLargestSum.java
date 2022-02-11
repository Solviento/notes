/*Given an array nums which consists of non-negative integers and an
integer m, you can split the array into m non-empty continuous subarrays.
Write an algorithm to minimize the largest sum among these m subarrays.

Example 1:

Input: nums = [7,2,5,10,8], m = 2
Output: 18
Explanation:
There are four ways to split nums into two subarrays.
The best way is to split it into [7,2,5] and [10,8],
where the largest sum among the two subarrays is only 18.

Example 2:
Input: nums = [1,2,3,4,5], m = 2
Output: 9

Example 3:
Input: nums = [1,4,4], m = 3
Output: 4*/

package com.practice.code.dynamicprogramming;

import com.practice.code.runner.CodeRunner;

public class SplitArrayLargestSum implements CodeRunner {
    @Override
    public void run() {

    }

    private int minimumSubarraysRequired(int[] nums, int maxSumAllowed) {
        int currentSum = 0;
        int splitsRequired = 0;

        for (int element : nums) {
            // Add element only if the sum doesn't exceed maxSumAllowed
            if (currentSum + element <= maxSumAllowed) {
                currentSum += element;
            } else {
                // If the element addition makes sum more than maxSumAllowed
                // Increment the splits required and reset sum
                currentSum = element;
                splitsRequired++;
            }
        }

        // Return the number of subarrays, which is the number of splits + 1
        return splitsRequired + 1;
    }

    public int splitArray(int[] nums, int m) {
        // Find the sum of all elements and the maximum element
        int sum = 0;
        int maxElement = Integer.MIN_VALUE;
        for (int element : nums) {
            sum += element;
            maxElement = Math.max(maxElement, element);
        }

        // Define the left and right boundary of binary search
        int left = maxElement;
        int right = sum;
        int minimumLargestSplitSum = 0;
        while (left <= right) {
            // Find the mid value
            int maxSumAllowed = left + (right - left) / 2;

            // Find the minimum splits. If splitsRequired is less than
            // or equal to m move towards left i.e., smaller values
            if (minimumSubarraysRequired(nums, maxSumAllowed) <= m) {
                right = maxSumAllowed - 1;
                minimumLargestSplitSum = maxSumAllowed;
            } else {
                // Move towards right if splitsRequired is more than m
                left = maxSumAllowed + 1;
            }
        }

        return minimumLargestSplitSum;
    }
}
