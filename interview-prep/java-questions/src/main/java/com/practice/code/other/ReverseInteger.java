/*Given a signed 32-bit integer x, return x with its digits reversed.
If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.

Assume the environment does not allow you to store 64-bit integers (signed or unsigned).

Example 1:
Input: x = 123
Output: 321*/
package com.practice.code.other;

import com.practice.code.runner.CodeRunner;

class ReverseInteger implements CodeRunner {
    public int reverse(int x) {
        // use long instead of int
        long result = 0;
        // could also do Math.abs to take care of negative cases and then return the negative int result
        while (x != 0) {
            int lastDig = x % 10;
            x = x / 10;
            // NOTE: this can cause overflow!!
            result = result * 10 + lastDig;
        }
        // this checks for overflow result operations
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            return 0;
        return (int) result;
    }

    @Override
    public void run() {

    }
}