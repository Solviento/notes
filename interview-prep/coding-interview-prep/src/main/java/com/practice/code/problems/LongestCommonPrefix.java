package com.practice.code.problems;

import java.util.Arrays;

// Write a function to find the longest common prefix string amongst an array of strings.
// If there is no common prefix, return an empty string "".
// Input: ["flower","flow","flight"]
// Output: "fl"
class LongestCommonPrefix {
  // Time: O(nlogn) Space: O(c) - c is length of string for longest string
  public String longestCommonPrefix(String[] strs) {
    if (strs.length == 0) {
      return "";
    }
    Arrays.sort(strs);
    String min = strs[0];
    String max = strs[strs.length - 1];
    int pointer = 0;
    int minLimit = min.length() - 1;
    int maxLimit = max.length() - 1;
    String prefix = "";
    while (pointer <= minLimit && pointer <= maxLimit && (min.charAt(pointer) == max.charAt(pointer))) {
      prefix += String.valueOf(min.charAt(pointer));
      pointer++;
    }
    return prefix;
  }
}