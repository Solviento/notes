/*Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
*Example 1:

Input: s = "leetcode"
Output: 0
* */

package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;


public class FirstUniqueChar implements CodeRunner {

    public int firstUniqueChar(String s) {
        // to save space, we can use int[] votes = new int[256]
        // and then do votes[c-'a']++; during the first for loop
        // in second for loop, just check for votes[c-'a'] == 1
        HashMap<Character, Integer> votes = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (votes.containsKey(c)) {
                int vote = votes.get(c);
                votes.put(c, vote + 1);
            } else {
                votes.put(c, 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int vote = votes.get(c);
            if (vote == 1) {
                return i;
            }
        }
        return -1;
    }

    public void run() {
        System.out.println("Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.");
        System.out.println("loveleetcode -> " + firstUniqueChar("loveleetcode"));
    }
}