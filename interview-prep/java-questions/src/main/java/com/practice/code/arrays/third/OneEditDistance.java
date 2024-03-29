/*Given two strings s and t, return true if
they are both one edit distance apart, otherwise return false.

A string s is said to be one distance apart from a string t if you can:

Insert exactly one character into s to get t.
Delete exactly one character from s to get t.
Replace exactly one character of s with a different character to get t.

Example 1:
Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.

Example 2:
Input: s = "", t = ""
Output: false
Explanation: We cannot get t from s by only one step.*/
package com.practice.code.arrays.third;

import com.practice.code.runner.CodeRunner;

public class OneEditDistance implements CodeRunner {
    @Override
    public void run() {

    }

    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if (Math.abs(m - n) > 1) return false;
        for (int i = 0; i < Math.min(m, n); i++) {
            if (s.charAt(i) == t.charAt(i)) continue;
            if (m == n) return s.substring(i + 1).equals(t.substring(i + 1));
            if (m > n) return s.substring(i + 1).equals(t.substring(i));
            else return s.substring(i).equals(t.substring(i + 1));
        }
        return m != n; /* Only last char different. eg."abcd" "abc". Rule out equal case "abc" "abc" */
    }
}
