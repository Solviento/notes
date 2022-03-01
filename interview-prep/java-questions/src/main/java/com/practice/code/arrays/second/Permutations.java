// Given a collection of distinct integers, return all possible permutations.
// Example:
// Input: [1,2,3]
// Output:
// [
//   [1,2,3],
//   [1,3,2],
//   [2,1,3],
//   [2,3,1],
//   [3,1,2],
//   [3,2,1]
// ]
// T: O(n*n!) why? There are n! permutations, recursive algorithm also requires to go through n iterations

package com.practice.code.arrays.second;

import java.util.ArrayList;
import java.util.List;

class Permutations {
    // requires backtracking recursive calls, can also use iterative methods
    // need use of i, left, right pointers to iterate and continuously
    // swap letters until all possible combinations are met
    public List<List<Integer>> permute(int[] nums) {
        int L = 0;
        int R = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (int i : nums) {
            numbers.add(i);
        }
        backtrackPermute(numbers, L, R, res);
        return res;
    }

    public void backtrackPermute(List<Integer> nums, int L, int R, List<List<Integer>> res) {
        if (L == R) {
            // res list cannot add nums, requires 'new' to store copy of nums current state
            res.add(new ArrayList<>(nums));
            return;
        }
        for (int i = L; i < R; i++) {
            swap(nums, i, L);
            backtrackPermute(nums, L + 1, R, res);
            swap(nums, i, L);
        }
    }

    public List<Integer> swap(List<Integer> nums, int i, int j) {
        int tmp = nums.get(i);
        int swapNum = nums.get(j);

        nums.set(i, swapNum);
        nums.set(j, tmp);
        return nums;
    }
}