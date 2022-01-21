/*Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.

Example 1:

Input: s = "()"
Output: true*/
package com.practice.code.problems;

import java.util.Stack;

public class ValidParenthesis {
    // add also hashmap implementation too

    // Time: O(n) Space: O(n)
    static boolean isValid(String s) {
        Stack<Character> stack_ = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            char currentChar = s.charAt(i);
            // (), {}, []
            if(currentChar == '(' || currentChar == '{' || currentChar == '['){
                stack_.push(currentChar);
            }
            if( (currentChar == ')' || currentChar == '}' || currentChar == ']')){
                if(stack_.isEmpty()){
                    return false;
                }
                char leftHalf = stack_.peek();
                if(leftHalf == '(' && currentChar != ')'){
                        return false;
                }
                if(leftHalf == '{' && currentChar != '}'){
                        return false;
                }
                if(leftHalf == '[' && currentChar != ']'){
                        return false;
                }
                // good match, pop stack
                stack_.pop();
            }
        }
        if(stack_.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
    public static void main(String... args){
        String s = "()[]";
        System.out.println(isValid(s));
    }
}