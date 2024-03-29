/*Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: strs = ["flower","flow","flight"]
Output: "fl"*/
package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;

public class LongestCommonPrefix implements CodeRunner {
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
        StringBuilder prefix = new StringBuilder();
        while (pointer <= minLimit &&
                pointer <= maxLimit &&
                (min.charAt(pointer) == max.charAt(pointer))) {
            prefix.append(min.charAt(pointer));
            pointer++;
        }
        return prefix.toString();
    }

    @Override
    public void run() {

    }
}