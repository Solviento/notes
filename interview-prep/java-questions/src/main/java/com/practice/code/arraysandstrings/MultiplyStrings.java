/*Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

https://www.youtube.com/watch?v=hDueaAjITi4
*/

package com.practice.code.arraysandstrings;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;

public class MultiplyStrings implements CodeRunner {

    public String multiply(String num1, String num2) {

        char[] chs1 = num1.toCharArray();
        char[] chs2 = num2.toCharArray();
        int n1 = chs1.length, n2 = chs2.length;
        char[] res = new char[n1 + n2];
        Arrays.fill(res, '0');

        for(int j = n2- 1; j >= 0; j--) {
            for(int i = n1 - 1; i >= 0; i--) {
                int product = (chs1[i] - '0') * (chs2[j] - '0');
                int tmp = res[i + j + 1] - '0' + product;
                res[i + j + 1] = (char) (tmp % 10 + '0');
                res[i + j] = (char) ((res[i + j] - '0' + tmp/10) + '0');
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean seen = false;
        for (char c : res) {
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
