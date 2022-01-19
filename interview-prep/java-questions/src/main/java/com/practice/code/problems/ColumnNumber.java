/*Given a string columnTitle that represents the column title as appear in an Excel sheet, return its corresponding column number.

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

public class ColumnNumber implements CodeRunner {
    static int titleToNumber(String s) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum *= 26;  // for each successive letter, multiply by 26 to fill up sum total and then add below
            sum += (s.charAt(i) - 'A') + 1; // converts A->1, .. Z->26
        }
        return sum;
    }

    public void run() {
        String s = "AB";
        System.out.println("Given a column title as it appears in an Excel sheet, return its corresponding column number");
        System.out.println(s + " -> " + titleToNumber(s));
    }
}