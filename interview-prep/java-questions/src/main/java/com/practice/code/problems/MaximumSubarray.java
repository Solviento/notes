/*Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

A subarray is a contiguous part of an array.

Example 1:

Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
https://www.youtube.com/watch?v=2MmGzdiKR9Y
*/
package com.practice.code.problems;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int localMax = nums[0];
        int globalMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            localMax = Math.max(localMax + nums[i], nums[i]);
            globalMax = Math.max(localMax, globalMax);
        }
        return globalMax;
    }
}