/*Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.

Example 1:

Input: s = "()"
Output: true*/
package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

import java.util.Stack;

public class ValidParenthesis implements CodeRunner {
    // add also hashmap implementation too

    // Time: O(n) Space: O(n)
    static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            // (), {}, []
            if (currentChar == '(' || currentChar == '{' || currentChar == '[') {
                stack.push(currentChar);
            }
            if ((currentChar == ')' || currentChar == '}' || currentChar == ']')) {
                if (stack.isEmpty()) {
                    return false;
                }
                char leftHalf = stack.peek();
                if (leftHalf == '(' && currentChar != ')') {
                    return false;
                }
                if (leftHalf == '{' && currentChar != '}') {
                    return false;
                }
                if (leftHalf == '[' && currentChar != ']') {
                    return false;
                }
                // good match, pop stack
                stack.pop();
            }
        }
        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {
        String s = "()[]";
        System.out.println(isValid(s));
    }
}