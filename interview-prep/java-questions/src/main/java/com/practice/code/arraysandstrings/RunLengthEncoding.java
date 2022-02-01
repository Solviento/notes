package com.practice.code.arraysandstrings;

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

        for(char c: inputChars) {
            if (previousChar == c) {
                counter++;
            }
            else {
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
