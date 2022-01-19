/*Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.

For example:

A -> 1
B -> 2
C -> 3
...
Z -> 26
AA -> 27
AB -> 28 */

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class ColumnTitle implements CodeRunner {
    static String convertToTitle(int n) {
        String s = "";
        while (n != 0) {
            // A->1 gets subtracted by 1, so when we add 'A', we get A
            // Z->26 get subtracted by 1, so when we add 'A', we get Z
            char c = (char) ((n - 1) % 26 + 'A');
            // append to left side
            s = String.valueOf(c) + s;
            // AZ->52, 52-1=51..51/26=1, 1 gets converted to A which is then used to form AZ
            n = (n - 1) / 26;
        }
        return s;
    }

    public void run() {
        int n = 52;
        String s = convertToTitle(n);
        System.out.println("Convert " + n + " to Title " + s);
    }
}