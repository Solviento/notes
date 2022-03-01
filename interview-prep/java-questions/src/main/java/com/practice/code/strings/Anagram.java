/*
* Given two strings s and t, return true if t
* is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging
* the letters of a different word or phrase, typically using all the original letters exactly once.
* */

package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;
import java.util.HashMap;

public class Anagram implements CodeRunner {
    // another solution is to sort the strings, use two pointers, if pointers dont match, then false OR use .equals()
    // sorting will require Time: O(nlogn) and Space: O(1)

    // by using a hashmap we can simply store the frequency of letters for the first string, the second string will then
    // match against the frequency values in the hashmap. If any frequency values are not equal to 0, then it is not an anagram
    // Time: O(n) Space: O(n)
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        // character mapped to frequency (int)
        HashMap<Character, Integer> map = new HashMap<>();
        // run through first string s
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // getOrDefault, useful to know
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // now run through second string t and decrement
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            //
            map.put(c, map.getOrDefault(c, 0) - 1);
        }
        // go through map and verify if counter is set to 0
        for (Character key : map.keySet()) {
            int val = map.get(key);
            if (val != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isAnagramSort(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    @Override
    public void run() {
        String s = "anagramaa";
        String t = "anagarmzz";
        boolean output = isAnagram(s, t);
        System.out.println("Are s and t anagrams? : " + s + " " + t + " : " + output);
    }
}