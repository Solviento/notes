/*You're given strings jewels representing the types of stones that are jewels, and stones representing the stones you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have are also jewels.

Letters are case sensitive, so "a" is considered a different type of stone from "A".

Input: jewels = "aA", stones = "aAAbbbb"
Output: 3
*/

package com.practice.code.arraysandstrings;

import com.practice.code.runner.CodeRunner;

import java.util.HashMap;

public class Jewels implements CodeRunner {
  public static int numJewelsInStones(String J, String S) {
    HashMap<Character, Integer> jewels = new HashMap<>();
    for (int i = 0; i < J.length(); i++) {
      jewels.put(J.charAt(i), 1);
    }
    int unique = 0;
    for (int j = 0; j < S.length(); j++) {
      if (jewels.containsKey(S.charAt(j))) {
        unique += 1;
      }
    }
    return unique;
  }

  @Override
  public void run() {

  }
}