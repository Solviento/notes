/*
Write an algorithm to determine if a number n is happy.
A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.

Input: 19
Output: true
Explanation:
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1 */

package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;

public class HappyNumber implements CodeRunner {

    public void run() {
        System.out.println("Is 19 a happy number? " + isHappy(19));
        System.out.println("Is 103 a happy number? " + isHappy(103));
    }

    public boolean isHappy(int n) {
        int loopCount = 0;
        boolean happy = false;
        while (!happy && loopCount < 1000) {
            int sumSquared = 0;
            while (n > 0) {
                // extract last digit
                int digit = (n % 10);
                sumSquared += digit * digit;
                // chop off last digit
                n = n / 10;
            }
            n = sumSquared;
            if (sumSquared == 1) {
                happy = true;
            }
            loopCount++;
        }
        return happy;
    }
}