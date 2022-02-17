/*Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.

Note that after backspacing an empty text, the text will continue empty.
Example 1:

Input: s = "ab#c", t = "ad#c"
Output: true
Explanation: Both s and t become "ac".

Example 2:
Input: s = "ab##", t = "c#d#"
Output: true
Explanation: Both s and t become "".
Example 3:

Input: s = "a#c", t = "b"
Output: false
Explanation: s becomes "c" while t becomes "b".
*/
package com.practice.code.arraysandstrings;

import com.practice.code.runner.CodeRunner;

import java.util.Stack;

public class BackspaceStringCompare implements CodeRunner {
    @Override
    public void run() {
        String s = "aa#c";
        String t = "bb#a";
        backspaceCompare(s, t);
    }

    // use native equals function to compare both strings after helper method returns string
    public boolean backspaceCompare(String S, String T) {
        return build(S).equals(build(T));
    }

    // use stack to push character, if # not encountered then push to stack
    // if # is encountered,
    public String build(String S) {
        Stack<Character> ans = new Stack<>();
        for (char c: S.toCharArray()) {
            if (c != '#')
                ans.push(c);
            else if (!ans.empty())
                ans.pop();
        }
        return String.valueOf(ans);
    }

}
