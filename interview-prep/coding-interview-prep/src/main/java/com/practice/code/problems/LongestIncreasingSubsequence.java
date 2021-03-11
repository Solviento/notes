package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

public class LongestIncreasingSubsequence implements CodeRunner {

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 1) {
            return 1;
        }
        int currentMax = 0;
        int bestMaxSoFar = 0;

        int currentLength = 1;
        int bestMaxLengthSoFar = 0;

        int previousNumber = nums[0];

        for(int i = 1; i < nums.length; i++){
            if(nums[i] < previousNumber) {
                if (currentMax > bestMaxSoFar){
                    bestMaxSoFar = currentMax;
                }
                if (currentLength > bestMaxLengthSoFar) {
                    bestMaxLengthSoFar = currentLength;
                }
                currentMax = 0;
                currentLength = 1;
            }
            if(nums[i] > previousNumber){
                previousNumber = nums[i];
                currentMax += previousNumber;
                currentLength++;
                if (currentMax > bestMaxSoFar){
                    bestMaxSoFar = currentMax;
                }
                if (currentLength > bestMaxLengthSoFar) {
                    bestMaxLengthSoFar = currentLength;
                }
            }
        }

        return bestMaxLengthSoFar;
    }

    public void run(){
        int[] nums = {0,1,0,3,2,3};
        int len = lengthOfLIS(nums);
        System.out.println("Longest increasing subsequence: " + len);
    }
}
