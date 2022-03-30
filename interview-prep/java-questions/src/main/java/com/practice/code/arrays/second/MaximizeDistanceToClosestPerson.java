package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

public class MaximizeDistanceToClosestPerson implements CodeRunner {
    @Override
    public void run() {

    }

    // two pointer pass solution
    public int maxDistToClosest(int[] nums) {
        int n = nums.length;
        int max = 0;
        int i = 0;
        // keep searching for groups of 0's and compare their total lengths to find maximum length
        while(i < n){
            while(i < n && nums[i] == 1){
                i++;
            }
            int j = i; //start
            while(i < n && nums[i] == 0){
                i++;
            }
            if(j == 0 || i == n){
                max = Math.max(max, i - j);
            }else{
                max = Math.max(max, (i - j + 1) / 2) ;
            }
        }
        return max;
    }
}
