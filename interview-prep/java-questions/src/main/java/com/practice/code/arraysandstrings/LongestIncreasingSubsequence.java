/*Given an integer array nums, return the length of the longest strictly increasing subsequence.

A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order
of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].
Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
*/

package com.practice.code.arraysandstrings;

import com.practice.code.runner.CodeRunner;

public class LongestIncreasingSubsequence implements CodeRunner {

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int currentMax = 0;
        int bestMaxSoFar = 0;

        int currentLength = 1;
        int bestMaxLengthSoFar = 0;

        int previousNumber = nums[0];

        for(int i = 1; i < nums.length; i++){
            if(nums[i] < previousNumber) {
                if (currentMax > bestMaxSoFar){
                    bestMaxSoFar = currentMax;
                }
                if (currentLength > bestMaxLengthSoFar) {
                    bestMaxLengthSoFar = currentLength;
                }
                currentMax = 0;
                currentLength = 1;
            }
            if(nums[i] > previousNumber){
                previousNumber = nums[i];
                currentMax += previousNumber;
                currentLength++;
                if (currentMax > bestMaxSoFar){
                    bestMaxSoFar = currentMax;
                }
                if (currentLength > bestMaxLengthSoFar) {
                    bestMaxLengthSoFar = currentLength;
                }
            }
        }

        return bestMaxLengthSoFar;
    }

    public void run(){
        int[] nums = {0,1,0,3,2,3};
        int len = lengthOfLIS(nums);
        System.out.println("Longest increasing subsequence: " + len);
    }
}
