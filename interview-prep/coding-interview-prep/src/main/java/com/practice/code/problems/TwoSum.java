package com.practice.code.problems;

// Given an array of integers, return indices of the two numbers such that they add up to a specific target.
// You may assume that each input would have exactly one solution, and you may not use the same element twice.
// Given nums = [2, 7, 11, 15], target = 9,
// Because nums[0] + nums[1] = 2 + 7 = 9,
// return [0, 1]

import java.util.HashMap;

class TwoSum {
  public int[] twoSum(int[] nums, int target) {
    if (nums.length == 1)
      return new int[] { 0, 0 };
    HashMap<Integer, Integer> hm = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (hm.containsKey(nums[i])) {
        return new int[] { hm.get(nums[i]), i };
      } else {
        int numDiff = target - nums[i];
        hm.put(numDiff, i);
      }
    }
    return new int[] { 0, 0 };
  }
  // 
  // 
}