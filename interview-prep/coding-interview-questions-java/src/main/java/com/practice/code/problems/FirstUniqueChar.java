package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;

// Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
// s = "leetcode"
// return 0.
// s = "loveleetcode",
// return 2
public class FirstUniqueChar implements CodeRunner {

  public int firstUniqChar(String s) {
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

  public void run(){
    System.out.println("Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.");
    System.out.println("loveleetcode -> " + firstUniqChar("loveleetcode"));
  }
}