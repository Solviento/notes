/*Given a string s and an integer k, return the length
of the longest substring of s that contains at most k distinct characters

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.*/

package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;

import java.util.LinkedHashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters implements CodeRunner {
    @Override
    public void run() {

    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int n = s.length();
        if (n * k == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int maxLength = 1;
        Map<Character, Integer> rightmostPosition = new LinkedHashMap<>();

        while (right < n) {
            Character character = s.charAt(right);
            rightmostPosition.remove(character);
            rightmostPosition.put(character, right++);

            if (rightmostPosition.size() == k + 1) {
                Map.Entry<Character, Integer> leftmost =
                        rightmostPosition.entrySet().iterator().next();
                rightmostPosition.remove(leftmost.getKey());
                left = leftmost.getValue() + 1;
            }

            maxLength = Math.max(maxLength, right - left);
        }
        return maxLength;
    }
}
