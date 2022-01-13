package com.practice.code.problems;

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