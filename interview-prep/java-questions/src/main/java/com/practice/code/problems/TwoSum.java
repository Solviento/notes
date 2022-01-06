package com.practice.code.problems;

// Given an array of integers, return indices of the two numbers such that they add up to a specific target.
// You may assume that each input would have exactly one solution, and you may not use the same element twice.
// Given nums = [2, 7, 11, 15], target = 9,
// Because nums[0] + nums[1] = 2 + 7 = 9,
// return [0, 1]

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;

public class TwoSum implements CodeRunner {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            if (!map.isEmpty() && map.containsKey(nums[i])) {
                result[0] = map.get(nums[i]);
                result[1] = i;
                return result;
            } else {
                int difference = target - nums[i];
                map.put(difference, i);
            }
        }
        return result;
    }

    @Override
    public void run() {

    }
}