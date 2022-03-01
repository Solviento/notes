/*Given an array of distinct integers candidates and a target integer target,
return a list of all unique combinations of candidates where the chosen
numbers sum to target. You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times.
Two combinations are unique if the frequency of at least one of the chosen numbers is different.
It is guaranteed that the number of unique combinations that sum up
to target is less than 150 combinations for the given input.

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]

Example 3:
Input: candidates = [2], target = 1
Output: []*/

package com.practice.code.backtracking;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumI implements CodeRunner {
    @Override
    public void run() {
        int[] nums = {2, 3};
        int target = 6;
        List<List<Integer>> output = combinationSum(nums, target);
        for(List<Integer> e: output) {
            System.out.println(e);
        }
    }

    // sort the array
    // backtrack
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    // output list, possible combinations list, possible values set
    private void backtrack(List<List<Integer>> list, List<Integer> comboList, int[] numberSet, int target, int start) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            list.add(new ArrayList<>(comboList));
        } else {
            // nums = {2, 3}, target = 6
            // for each possible number, add it to the combinations list, then backtrack using this list
            for (int i = start; i < numberSet.length; i++) {
                comboList.add(numberSet[i]);
                // 6 - 2 = 4
                // target - nums[i]
                backtrack(list, comboList, numberSet, target - numberSet[i], i); // not i + 1 because we can reuse same elements
                comboList.remove(comboList.size() - 1);
            }
        }
    }

}
