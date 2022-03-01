/*Given two non-negative integers num1 and num2 represented
as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library
or convert the inputs to integer directly.

https://www.youtube.com/watch?v=hDueaAjITi4
*/

package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;

public class MultiplyStrings implements CodeRunner {

    public String multiply(String num1, String num2) {

        char[] firstNumber = num1.toCharArray();
        char[] secondNumber = num2.toCharArray();
        int n1 = firstNumber.length, n2 = secondNumber.length;
        char[] resultNumber = new char[n1 + n2];
        Arrays.fill(resultNumber, '0');

        for (int j = n2 - 1; j >= 0; j--) {
            for (int i = n1 - 1; i >= 0; i--) {
                int product = (firstNumber[i] - '0') * (secondNumber[j] - '0');
                int tmp = resultNumber[i + j + 1] - '0' + product;
                resultNumber[i + j + 1] = (char) (tmp % 10 + '0');
                resultNumber[i + j] = (char) ((resultNumber[i + j] - '0' + tmp / 10) + '0');
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean seen = false;
        for (char c : resultNumber) {
            if (c == '0' && !seen) {
                continue;
            }
            sb.append(c);
            seen = true;
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    @Override
    public void run() {

    }
}
