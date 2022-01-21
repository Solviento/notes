/*Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Example 1:

Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].*/
package com.practice.code.problems;

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