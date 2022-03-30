/*A binary string is monotone increasing if it consists of
some number of 0's (possibly none), followed by some number of 1's (also possibly none).
You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.
Return the minimum number of flips to make s monotone increasing.

Example 1:
Input: s = "00110"
Output: 1
Explanation: We flip the last digit to get 00111.

Example 2:
Input: s = "010110"
Output: 2
Explanation: We flip to get 011111, or alternatively 000111.

Example 3:
Input: s = "00011000"
Output: 2
Explanation: We flip to get 00000000.*/

package com.practice.code.strings;

import com.practice.code.runner.CodeRunner;

public class FlipStringToMonotoneIncreasing implements CodeRunner {
    @Override
    public void run() {

    }

    // t: n s: 1
    public int minFlipsMonoIncr(String S) {
        int one = 0;
        int flip = 0;
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '1')
                one++;
            else
                flip++;
            flip = Math.min(one, flip);
        }
        return flip;
    }

    // t: n s: n
    public int minFlipsMonoIncrN(String S) {
        int N = S.length();
        int[] P = new int[N + 1];
        for (int i = 0; i < N; ++i)
            P[i+1] = P[i] + (S.charAt(i) == '1' ? 1 : 0);

        int ans = Integer.MAX_VALUE;
        for (int j = 0; j <= N; ++j) {
            ans = Math.min(ans, P[j] + N-j-(P[N]-P[j]));
        }

        return ans;
    }
}
