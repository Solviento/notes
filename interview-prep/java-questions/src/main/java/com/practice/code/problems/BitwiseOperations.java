/*
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * */
package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class BitwiseOperations implements CodeRunner {

    public int getSum(int a, int b) {
        // a = 0101 (5)
        // b = 0101 (5)
        // a+b = 1010 (10)
        while (b != 0) {
            //   0101
            // & 0101
            // = 0101 (the leftover carry!)
            int carry = a & b;
            //   0101
            // ^ 0101
            // = 0000
            a = a ^ b;
            // 0101 << 1 = 1010 (10)
            // note: in the second iteration, b is now 1010 and a remains 0101 so carry = 1010 & 0101 = 0000,
            // then a = 0000 ^ 1010 = 1010, finally after b = 0000 and loop ends
            b = carry << 1;
        }
        return a;
    }

    @Override
    public void run() {
        int a = 4;
        int b = 10;
        System.out.println("Output of " + a + " + " + b + " using bitwise operations : " + getSum(a, b));
    }
}