/*Given an encoded string, return its decoded string.
The encoding rule is: k[encoded_string], where the encoded_string
inside the square brackets is being repeated exactly k times.
Note that k is guaranteed to be a positive integer.
You may assume that the input string is always valid; there
are no extra white spaces, square brackets are well-formed, etc.
Furthermore, you may assume that the original data does not contain
any digits and that digits are only for those repeat numbers, k.
For example, there will not be input like 3a or 2[4].

Example 1:
Input: s = "3[a]2[bc]"
Output: "aaabcbc"

Example 2:
Input: s = "3[a2[c]]"
Output: "accaccacc"

Example 3:
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"*/

package com.practice.code.trees;

import com.practice.code.runner.CodeRunner;

import java.util.Stack;

public class DecodeString implements CodeRunner {
    @Override
    public void run() {
        String s = "2[abc]";
        String res = decodeString(s);
        System.out.println("Decoding: " + res);
    }

    // keep two stacks for storing the integers and chars
    // 2[abc] -< [2] | [abcabc]
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                intStack.push(k);
                strStack.push(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder tmp = cur;
                cur = strStack.pop();
                for (k = intStack.pop(); k > 0; --k) {
                    cur.append(tmp);
                }
            } else {
                cur.append(ch);
            }
        }
        return cur.toString();
    }
}
