/*You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are in the inclusive range.

A number x is considered missing if x is in the range [lower, upper] and x is not in nums.

Return the smallest sorted list of ranges that cover every missing number exactly. That is, no element of nums is in any of the ranges, and each missing number is in one of the ranges.

Each range [a,b] in the list should be output as:

"a->b" if a != b
"a" if a == b*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.List;

public class MissingRanges implements CodeRunner {

    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            result.add(formatRange(lower, upper));
            return result;
        }

        // 1st step - check range for first element
        if (nums[0] > lower) {
            result.add(formatRange(lower, nums[0] - 1));
        }

        // 2nd step - check all ranges for each middle elements
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i] + 1) {
                result.add(formatRange(nums[i] + 1, nums[i + 1] - 1));
            }
        }

        // 3rd step - check last element and add remaining range
        if (nums[nums.length - 1] < upper) {
            result.add(formatRange(nums[nums.length - 1] + 1, upper));
        }
        return result;
    }

    public String formatRange(int low, int high) {
        if (low == high) {
            return String.valueOf(low);
        } else {
            return low + "->" + high;
        }
    }

    @Override
    public void run() {
        int[] nums = {0, 1, 3, 50, 75};
        int lower = 0; int higher = 99;
        List<String> res = findMissingRanges(nums, lower, higher);
        for (String s: res){
            System.out.println(s);
        }
    }
}
