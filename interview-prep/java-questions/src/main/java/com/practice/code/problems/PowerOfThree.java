/*Given an integer n, return true if it is a power of three. Otherwise, return false.

An integer n is a power of three, if there exists an integer x such that n == 3x.

Example 1:

Input: n = 27
Output: true*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

class PowerOfThree implements CodeRunner {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        // if y = b^x then x=log_b(y)
        double rem = Math.log10(n) / Math.log10(3);
        // if rem is a whole number, then n is a power of 3
        // else, if not whole number then n is not a power of 3
        System.out.println(rem);
        if (rem % 1 == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void run() {

    }
}