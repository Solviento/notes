// Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
// Input: [1,2,3]
// Output: [1,2,4]
// Explanation: The array represents the integer 123.

package com.practice.code.arraysandstrings;

class PlusOneArray {
    public int[] plusOne(int[] digits) {
        // perform increment
        digits[digits.length - 1] += 1;
        // loop through digits aray to see if increment causes an additional carry
        for (int i = digits.length - 1; i > 0; i--) {
            if (digits[i] > 9) {
                digits[i] = 0;
                digits[i - 1] = digits[i - 1] + 1;
            }
        }
        int[] digitsExt = new int[digits.length + 1];
        if (digits[0] == 10) {
            // digits: [10][0][0] -> [0][0][0]
            digits[0] = 0;
            // copy digits array (from index 1) over to digitsExt array
            System.arraycopy(digits, 0, digitsExt, 1, digits.length);
            // digitsExt: [][0][0][0] -> [1][0][0][0]
            digitsExt[0] = 1;
            digits = digitsExt;
        }
        return digits;
    }
}