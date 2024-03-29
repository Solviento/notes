/*Given two strings s and p, return an array of all the
start indices of p's anagrams in s. You may return the answer in any order.
An Anagram is a word or phrase formed by rearranging the
letters of a different word or phrase, typically using all the original letters exactly once.

Example 1:
Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".

Example 2:
Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".*/

package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString implements CodeRunner {
    @Override
    public void run() {

    }

    public List<Integer> findAnagrams(String s, String p) {
        int lengthS = s.length();
        int lengthP = p.length();
        if (lengthS < lengthP) {
            return new ArrayList<>();
        }
        Map<Character, Integer> pCount = new HashMap<>();
        Map<Character, Integer> sCount = new HashMap<>();
        // build reference hashmap using string p
        for (char ch : p.toCharArray()) {
            if (pCount.containsKey(ch)) {
                pCount.put(ch, pCount.get(ch) + 1);
            } else {
                pCount.put(ch, 1);
            }
        }

        List<Integer> output = new ArrayList<>();
        // sliding window on the string s
        for (int i = 0; i < lengthS; ++i) {
            // add one more letter on the right side of the window
            char ch = s.charAt(i);
            if (sCount.containsKey(ch)) {
                sCount.put(ch, sCount.get(ch) + 1);
            } else {
                sCount.put(ch, 1);
            }
            // remove one letter from the left side of the window
            if (i >= lengthP) {
                ch = s.charAt(i - lengthP);
                if (sCount.get(ch) == 1) {
                    sCount.remove(ch);
                } else {
                    sCount.put(ch, sCount.get(ch) - 1);
                }
            }
            // compare hashmap in the sliding window with the reference hashmap
            if (pCount.equals(sCount)) {
                output.add(i - lengthP + 1);
            }
        }
        return output;
    }

}
