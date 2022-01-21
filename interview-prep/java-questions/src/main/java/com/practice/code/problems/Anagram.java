/*
* Given two strings s and t, return true if t is an anagram of s, and false otherwise.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.
* */

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

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
        // can be used for unicode letters as well
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 2) - 1);
        }
        for (Character key : map.keySet()) {
            int val = map.get(key);
            if (val != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        String s = "anagramq";
        String t = "nagarams";
        boolean output = isAnagram(s, t);
        System.out.println("Are s and t anagrams? : " + s + " " + t + " : " + output);
    }
}