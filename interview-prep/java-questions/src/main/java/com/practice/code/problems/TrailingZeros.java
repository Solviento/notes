/*Given an integer n, return the number of trailing zeroes in n!.

Note that n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1.

Example 1:

Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.*/
package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class TrailingZeros implements CodeRunner {
    public int trailingZeroes(int n) {
        int count = 0;
        for (long i = 5; n / i > 0; i *= 5)
            count += n / i;
        return count;
        // OR
        /*
        int zeros = 0;
        while(n){
            zeros += n/= 5;
        }
        return zeros;
        */
    }

    @Override
    public void run() {

    }
}