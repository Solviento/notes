/*Given a string s, find the first non-repeating character
in it and return its index. If it does not exist, return -1.

Example 1:
Input: s = "leetcode"
Output: 0

Example 2:
Input: s = "loveleetcode"
Output: 2

Example 3:
Input: s = "aabb"
Output: -1*/

package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;


public class FirstUniqueChar implements CodeRunner {

    public void run() {
        System.out.println("Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.");
        System.out.println("loveleetcode -> " + firstUniqueChar("loveleetcode"));
    }

    public int firstUniqueChar(String s) {
        // character frequency map
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            frequencyMap.merge(ch, 1, (x, y) -> x + y);
        }
        // if frequency of char is found, return index
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int frequency = frequencyMap.get(c);
            if (frequency == 1) {
                return i;
            }
        }
        return -1;
    }

    // fixed array
    public int firstUniqChar(String s) {
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++)
            // to store in fixed array, subtract char 'a' from char element
            // 'a' - 'a' = 0, 'b' - 'a' = 1, etc.
            freq[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++)
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        return -1;
    }
}