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
        System.out.println("decided string: " + res);
    }

    // keep two stacks for storing the integers and chars
    // 2[abc] -< [2] | [abcabc]
    public String decodeString(String s) {
        Stack<Integer> intStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder decodedString = new StringBuilder();
        int multiple = 0;
        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
//                k = k * 10 + ch - '0';
                multiple = Character.getNumericValue(ch);
            } else if (ch == '[') {
                intStack.push(multiple);
                strStack.push(decodedString);
                decodedString = new StringBuilder();
                // reset multiple for next character decoding
                multiple = 0;
            } else if (ch == ']') {
                StringBuilder tmpLetter = decodedString;
                decodedString = strStack.pop();
                for (multiple = intStack.pop(); multiple > 0; --multiple) {
                    decodedString.append(tmpLetter);
                }
            } else {
                decodedString.append(ch);
            }
        }
        return decodedString.toString();
    }
}
