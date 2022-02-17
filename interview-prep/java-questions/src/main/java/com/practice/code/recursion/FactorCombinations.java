/*Numbers can be regarded as the product of their factors.
For example, 8 = 2 x 2 x 2 = 2 x 4.
Given an integer n, return all possible combinations
of its factors. You may return the answer in any order.
Note that the factors should be in the range [2, n - 1].

Example 1:
Input: n = 1
Output: []

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

    }

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        helper(result, new ArrayList<Integer>(), n, 2);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> item, int n, int start){
        if (n <= 1) {
            if (item.size() > 1) {
                result.add(new ArrayList<Integer>(item));
            }
            return;
        }

        for (int i = start; i <= n; ++i) {
            if (n % i == 0) {
                item.add(i);
                helper(result, item, n/i, i);
                item.remove(item.size()-1);
            }
        }
    }
}
