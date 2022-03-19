/*You are given a large integer represented as an
integer array digits, where each digits[i] is the
ith digit of the integer. The digits are ordered
from most significant to least significant in
left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.

https://www.youtube.com/watch?v=_sls9AdBymI

Example 1:
Input: digits = [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Incrementing by one gives 123 + 1 = 124.
Thus, the result should be [1,2,4].

Example 2:
Input: digits = [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
Incrementing by one gives 4321 + 1 = 4322.
Thus, the result should be [4,3,2,2].
*/

package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

public class PlusOne implements CodeRunner {

    @Override
    public void run() {

    }

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
}
