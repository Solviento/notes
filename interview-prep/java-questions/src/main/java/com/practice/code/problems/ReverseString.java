/*Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Input: s = ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
*/
package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

class ReverseString implements CodeRunner {
  static void reverseString(char[] s) {
    int left = 0, right = s.length - 1;
    while (left < right) {
      char tmp = s[right];
      s[right] = s[left];
      s[left] = tmp;
      left++;
      right--;
    }
  }

  @Override
  public void run() {
    char[] c = new char[]{'H', 'a', 'p', 'p', 'y'};
    reverseString(c);
  }
}