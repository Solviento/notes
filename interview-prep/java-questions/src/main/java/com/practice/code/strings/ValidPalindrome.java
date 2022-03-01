/*A phrase is a palindrome if, after converting all
uppercase letters into lowercase letters and removing
all non-alphanumeric characters, it reads the same
forward and backward. Alphanumeric characters include letters and numbers.
Given a string s, return true if it is a palindrome, or false otherwise.

Input: s = "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama" is a palindrome.
*/
package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

public class ValidPalindrome implements CodeRunner {

    // faster, no built ins (no replace all) also using two pointers
    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            if (!Character.isLetterOrDigit(l)) {
                left++;
                continue;
            } else if (!Character.isLetterOrDigit(r)) {
                right--;
                continue;
            } else if (Character.toUpperCase(l) != Character.toUpperCase(r)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
    // input is only lowercase abc AND input can be at MOST off by one letter
    // aabnanbcaa
    // once left has reached aa and right has reached aa, it will reach b and c, to verify it is only one off simply
    // take the sub arrays of BOTH left+1 and right-1
    public static boolean validPalindromeII(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            if (l != r) {
                // break string into two parts
                boolean left_ = subPalindrome(s, left + 1, right);
                boolean right_ = subPalindrome(s, left, right - 1);
                return left_ || right_;
            }
            left++;
            right--;
        }
        return true;
    }

    // helper method
    static boolean subPalindrome(String s, int left, int right) {
        while (left < right) {
            char l = s.charAt(left);
            char r = s.charAt(right);
            if (l != r) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    @Override
    public void run() {

    }
}