package com.practice.code.problems;

import java.util.HashMap;

// Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
// Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
class RomanInteger {
  static int romanToInt(String s) {
    HashMap<Character, Integer> map = new HashMap<>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X', 10);
    map.put('L', 50);
    map.put('C', 100);
    map.put('D', 500);
    map.put('M', 1000);
    int sum = 0;
    // VII
    // We calculate total for just VI, we take care of last I right after
    for (int i = s.length() - 2; i > -1; i--) {
      char cLeft = s.charAt(i), cRight = s.charAt(i + 1);
      int eLeft = map.get(cLeft), eRight = map.get(cRight);
      // VI -> +5
      if (eLeft >= eRight) {
        sum += eLeft;
      }
      // IV -> -1
      else {
        sum -= eLeft;
      }
    }
    // VI -> +1, IV -> +5
    sum += map.get(s.charAt(s.length() - 1));
    return sum;
  }
  public static void main(String... args){
    String s = "IX";
    int n = romanToInt(s);

  }
}