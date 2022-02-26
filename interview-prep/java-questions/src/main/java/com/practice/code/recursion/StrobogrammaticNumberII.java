/*Given an integer n, return all the strobogrammatic numbers that are of length n. You may return the answer in any order.
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Example 1:
Input: n = 2
Output: ["11","69","88","96"]
Example 2:

Input: n = 1
Output: ["0","1","8"]*/

package com.practice.code.recursion;

import com.practice.code.runner.CodeRunner;

import java.util.*;

public class StrobogrammaticNumberII implements CodeRunner {
    @Override
    public void run() {

    }

    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    List<String> helper(int n, int m) {
        if (n == 0) return new ArrayList<>(Arrays.asList(""));
        if (n == 1) return new ArrayList<>(Arrays.asList("0", "1", "8"));

        List<String> list = helper(n - 2, m);

        List<String> res = new ArrayList<String>();

        for (String s : list) {
            if (n != m) res.add("0" + s + "0");

            res.add("1" + s + "1");
            res.add("6" + s + "9");
            res.add("8" + s + "8");
            res.add("9" + s + "6");
        }

        return res;
    }


    public char[][] reversiblePairs = {
            {'0', '0'}, {'1', '1'},
            {'6', '9'}, {'8', '8'}, {'9', '6'}
    };

    public List<String> findStrobogrammaticIterative(int n) {
        Queue<String> q = new LinkedList<>();
        int currStringsLength;

        // When n is even, it means when decreasing by 2 we will go till 0.
        if (n % 2 == 0) {
            // We will start with 0-digit strobogrammatic numbers.
            currStringsLength = 0;
            q.add("");
        } else {
            // We will start with 1-digit strobogrammatic numbers.
            currStringsLength = 1;
            q.add("0");
            q.add("1");
            q.add("8");
        }

        while (currStringsLength < n) {
            currStringsLength += 2;
            for (int i = q.size(); i > 0; --i) {
                String number = q.poll();

                for (char[] pair : reversiblePairs) {
                    if (currStringsLength != n || pair[0] != '0') {
                        q.add(pair[0] + number + pair[1]);
                    }
                }
            }
        }

        List<String> stroboNums = new ArrayList<>();
        while (!q.isEmpty()) {
            stroboNums.add(q.poll());
        }

        return stroboNums;
    }

}
