package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum implements CodeRunner {

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> output_arr = new LinkedList<>();
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int low = i + 1;
                int high = nums.length - 1;
                int sum = 0 - nums[i];
            }
        }
        return null;
    }

    @Override
    public void run() {

    }
}
