package com.practice.code.problems;
// Given an array of integers, find if the array contains any duplicates.
// Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct
// Input: [1,2,3,1]
// Output: true
import com.practice.code.runner.CodeRunner;

import java.util.Set;
import java.util.HashSet;
public class ContainsDuplicate implements CodeRunner {

  // Alternative: sort the array, then use two pointers to iterate and check for duplicates
  // Time: O(nlogn) Space: O(1)
  // When to use? If given very large array, compromise would be suitable

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
  public void run(){
    int[] nums = {1, 2, 3, 1};
    System.out.println("Given an array of integers, find if the array contains any duplicates.");
    System.out.println("1, 2, 3, 1 - > " + containsDuplicate(nums));
  }
}