/*Given two strings s and t of lengths m and n respectively,
return the minimum window substring of s such that every
character in t (including duplicates) is included in the window.
If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.

Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
https://www.youtube.com/watch?v=0GOyCIJ2ajQ
*/

package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

public class MinimumWindowSubstring implements CodeRunner {

    public String minWindow(String s, String t) {

        if (s == null || t == null) {
            return "";
        }
        String outputString = "";
        int[] letterCount = new int[128];
        int left = 0;
        int count = 0;
        int minimumSubstringLength = Integer.MAX_VALUE;
        // create character frequency array map of target string t
        for (char c : t.toCharArray()) {
            ++letterCount[c];
        }
        for (int right = 0; right < s.length(); ++right) {
            // selected character was found in array map, increase count
            if (--letterCount[s.charAt(right)] >= 0) {
                ++count;
            }
            // sufficient t characters were found in the substring
            while (count == t.length()) {
                // compare minimum lengths, if latest one is shortest then use it
                if (minimumSubstringLength > right - left + 1) {
                    minimumSubstringLength = right - left + 1;
                    // extract
                    outputString = s.substring(left, right + 1);
                }
                //
                if (++letterCount[s.charAt(left)] > 0) {
                    --count;
                }
                ++left;
            }
        }
        return outputString;
    }

    @Override
    public void run() {

        String s = "ADOBECODEBANC";
        String t = "ABC";
        String res = minWindow(s, t);
        System.out.println("Minimum substring: " + res);
    }
}
