/*You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

https://www.youtube.com/watch?v=_sls9AdBymI
*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class PlusOne implements CodeRunner {

    public int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] = digits[i] + 1;
                return digits;
            }
            // if 9 is encountered, the one will be carried over in the next operation by digits[i] + 1
            digits[i] = 0;
        }
        // if something like [9, 9] is encountered then values will be overwritten by above code to [0, 0]
        // create new array and append 1 to the front
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    @Override
    public void run() {

    }
}
