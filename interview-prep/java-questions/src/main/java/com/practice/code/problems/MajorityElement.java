package com.practice.code.problems;
import java.util.HashMap;
// Given an array of size n, find the majority element. The majority element is the element
// that appears MORE THAN n/2 times, may assume array is non empty and majority element DOES exist
public class MajorityElement {
  static int majorityElement(int[] nums) {
    HashMap<Integer, Integer> maj = new HashMap<>();
    for (Integer e : nums) {
      if (!maj.containsKey(e)) {
        maj.put(e, 1);
      } else {
        int c = maj.get(e);
        maj.put(e, c + 1);
        // threshold for majority
        if (maj.get(e) > (nums.length / 2)) {
          return e;
        }
      }
    }
    // if array is one element, return element
    return nums[0];
  }
}