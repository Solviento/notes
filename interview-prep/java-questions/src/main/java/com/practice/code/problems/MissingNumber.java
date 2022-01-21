/*Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

Example 1:

Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.*/
package com.practice.code.problems;

public class MissingNumber {
    public int missingNumber(int[] nums) {
        // Equation - sum of 0+1+2+..+n = n(n+1) / 2
        int n = nums.length;
        int seriesSum = n * (n + 1) / 2;
        for (int num : nums) {
            seriesSum -= num;
        }
        return seriesSum;
    }
}