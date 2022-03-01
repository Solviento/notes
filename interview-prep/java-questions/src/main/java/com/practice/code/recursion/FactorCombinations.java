/*Numbers can be regarded as the product of their factors.
For example, 8 = 2 x 2 x 2 = 2 x 4.
Given an integer n, return all possible combinations
of its factors. You may return the answer in any order.
Note that the factors should be in the range [2, n - 1].

Example 1:
Input: n = 1
Output: [] - (1 is less than 2)

Example 2:
Input: n = 12
Output: [[2,6],[3,4],[2,2,3]]

Example 3:
Input: n = 37
Output: []*/

package com.practice.code.recursion;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.List;

public class FactorCombinations implements CodeRunner {
    @Override
    public void run() {
        int n = 4;
        List<List<Integer>> list = getFactors(n);
        for(List<Integer> l : list) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        // n = 4, start = 2
        helper(result, new ArrayList<>(), n, 2);
        return result;
    }

    // DFS Approach
    public void helper(List<List<Integer>> result, List<Integer> item, int n, int start) {
        if (n <= 1) {
            if (item.size() > 1) {
                result.add(new ArrayList<>(item));
            }
            return;
        }

        for (int i = start; i <= n; ++i) {
            if (n % i == 0) {
                item.add(i);
                helper(result, item, n / i, i);
                item.remove(item.size() - 1);
            }
        }
    }
}
