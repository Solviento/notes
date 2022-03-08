/*Given an integer array nums, return true if any value
appears at least twice in the array, and return false if every element is distinct.

Example 1:
Input: nums = [1,2,3,1]
Output: true

Example 2:
Input: nums = [1,2,3,4]
Output: false

Example 3:
Input: nums = [1,1,1,3,3,4,3,2,4,2]
Output: true*/

package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class ContainsDuplicate implements CodeRunner {

    public void run() {
        int[] nums = {1, 2, 3, 1};
        System.out.println("Given an array of integers, find if the array contains any duplicates.");
        System.out.println("1, 2, 3, 1 - > " + containsDuplicate(nums));
    }

    // Iterate through array, store all integers in a set and check for duplicates
    // Time: O(n) Space: O(n)
    // not suitable for very large arrays
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (Integer e : nums) {
            if (set.contains(e)) {
                return true;
            } else {
                set.add(e);
            }
        }
        return false;
    }

    // t: o(nlogn) s: o(1)
    public boolean containsDuplicateLogn(int[] nums) {
        Arrays.sort(nums);
        for(int ind = 1; ind < nums.length; ind++) {
            if(nums[ind] == nums[ind - 1]) {
                return true;
            }
        }
        return false;
    }
}