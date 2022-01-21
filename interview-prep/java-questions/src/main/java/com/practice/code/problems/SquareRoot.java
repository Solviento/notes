/*Given a non-negative integer x, compute and return the square root of x.

Since the return type is an integer, the decimal digits are truncated, and only the integer part of the result is returned.

Note: You are not allowed to use any built-in exponent function or operator, such as pow(x, 0.5) or x ** 0.5.

Example 1:

Input: x = 4
Output: 2*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class SquareRoot implements CodeRunner {
    int mySqrt(int x) {
        if (x == 0 || x == 1) return x;
        // start=1 to avoid overflow, if start=0 and x=1,end=1 then mid=0 which leads to division by 0
        int start = 1, end = x;
        int ans = 0;
        while (start <= end) {
            // (start+end)/2 might result in overflow (x being largest int) so we use this operation instead
            int mid = start + (end - start) / 2;
            // mid*mid == x might also result in overflow, so we use this robust operation to calculate mid
            if (mid <= x / mid) {
                start = mid + 1;
                ans = mid;
            } else
                end = mid - 1;
        }
        return ans;
    }

    @Override
    public void run() {

    }
}