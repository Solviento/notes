/*Given a string, Your task is to  complete the function encode
that returns the run length encoded string for the given string.
eg if the input string is “wwwwaaadexxxxxx”, then the function should return “w4a3d1e1x6″.
You are required to complete the function encode that
takes only one argument the string which is to be encoded and returns the encoded string.

Example 1:
Input:
str = aaaabbbccc
Output: a4b3c3
Explanation: a repeated 4 times
consecutively b 3 times, c also 3
times.

Example 2:
Input:
str = abbbcdddd
Output: a1b3c1d4*/
package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

public class RunLengthEncoding implements CodeRunner {

    public String encode(String input) {
        // check for null/ empty input
        if (input == null || input.length() == 0)
            return "";

        char[] inputChars = input.toCharArray();
        char previousChar = inputChars[0];
        int counter = 0;
        StringBuilder output = new StringBuilder();

        for (char c : inputChars) {
            if (previousChar == c) {
                counter++;
            } else {
                output.append(counter).append(previousChar);
                previousChar = c;
                counter = 1;
            }
        }
        output.append(counter).append(previousChar);
        return output.toString();
    }

    public void run() {
        String a = "aaaabbccca";
        String out = encode(a);
        System.out.println("Encoding of aaaabbccca : " + out);

        String b = "aaaaa";
        String outb = encode(b);
        System.out.println("Encoding of aaaaa : " + outb);

        String c = "aaaaaba";
        String outc = encode(c);
        System.out.println("Encoding of aaaaaba : " + outc);
    }
}
