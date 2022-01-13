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
                // sorted list, pick on upper and lower boundaries for match
                int low = i + 1;
                int high = nums.length - 1;
                int sum = 0 - nums[i];
                while(low < high) {
                    if (nums[low] + nums[high] == sum) {
                        output_arr.add(Arrays.asList(nums[i], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) {
                            low++;
                        }
                        while (low < high && nums[high] == nums[high - 1]) {
                            high--;
                        }
                        // increment
                        low++;
                        high--;
                    } else if (nums[low] + nums[high] > sum) {
                        high--;
                    } else {
                        low++;
                    }
                }
            }
        }
        return output_arr;
    }

    @Override
    public void run() {
        int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> output = threeSum(nums);
        System.out.println("Output : " + output);
    }
}
