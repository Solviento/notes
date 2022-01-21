/*Given a string s, return the string after replacing every uppercase letter with the same lowercase letter.

Example 1:

Input: s = "Hello"
Output: "hello"*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class ToLowerCase implements CodeRunner {
  public static String toLowerCase_(String str) {
    char[] c = str.toCharArray();
    for (int i = 0; i < str.length(); i++) {
      Character ch = str.charAt(i);
      if (ch >= 'A' && ch <= 'Z'){
        c[i] = (char) (ch + 32);
      }
    }
    return String.valueOf(c);
  }

  @Override
  public void run() {
    String h = "HelloABXZz";
    String h_ = toLowerCase_(h);
    System.out.println(h_);
  }
}