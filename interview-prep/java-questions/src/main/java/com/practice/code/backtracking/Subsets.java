/*Given an integer array nums of unique elements, return all possible subsets (the power set).

The solution set must not contain duplicate subsets. Return the solution in any order.

Example 1:

Input: nums = [1,2,3]
Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]*/

package com.practice.code.backtracking;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.List;

public class Subsets implements CodeRunner {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    void backtrack(List<List<Integer>> result, List<Integer> tmpList, int[] arr, int start) {
        result.add(new ArrayList<>(tmpList));
        for (int i = start; i < arr.length; i++) {
            tmpList.add(arr[i]);
            backtrack(result, tmpList, arr, i + 1);
            tmpList.remove(tmpList.size() - 1);
        }
    }

    @Override
    public void run() {

    }
}