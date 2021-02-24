package com.practice.code.problems;
// Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
// input: [3, 0, 1] output: 2
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