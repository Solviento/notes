/*

Given a string s, return the length of the longest substring that contains at most two distinct characters.

https://www.youtube.com/watch?v=gj1Y-enAlXM
 */
package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringTwoDistinctCharacters implements CodeRunner {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // base case
        char[] arr = s.toCharArray();
        int stringLength = arr.length;
        if (stringLength < 3) {
            return stringLength;
        }
        // define pointers
        int left = 0;
        int right = 0;
        // define table
        Map<Character, Integer> hm = new HashMap<>();
        // define maxLen
        int maxLen = 0;
        // find the max len substring 2 distinct char
        while(right < stringLength) {
            hm.put(arr[right], hm.getOrDefault(arr[right], 0) + 1);
            // contract our window if we dont meet the condition
            while(hm.size() > 2) {
                hm.put(arr[left], hm.get(arr[left]) - 1);
                if (hm.get(arr[left]) == 0) {
                    hm.remove(arr[left]);
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        return maxLen;
    }

    @Override
    public void run() {


    }
}
