// Given a string s, find the length of the longest substring without repeating characters.

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

import java.util.HashSet;

public class LengthOfLongestSubstring implements CodeRunner {

    // sliding window problem
    public int lengthOfLongestSubstring(String s) {
        int a_pointer = 0;
        int b_pointer = 0;
        int max = 0;

        HashSet<Character> hash_set = new HashSet<>();
        while (b_pointer < s.length()) {
            if (!hash_set.contains(s.charAt(b_pointer))) {
                hash_set.add(s.charAt(b_pointer));
                b_pointer++;
                max = Math.max(hash_set.size(), max);
            } else {
                hash_set.remove(s.charAt(a_pointer));
                a_pointer++;
            }
        }
        return max;
    }

    @Override
    public void run() {
        String s = "pwwkew";
        int length = lengthOfLongestSubstring(s);
        System.out.println("Length of longest substring: " + length);
    }
}
