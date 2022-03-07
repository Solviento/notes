/*Given an integer array nums, return the
length of the longest strictly increasing subsequence.
A subsequence is a sequence that can be derived from
an array by deleting some or no elements without changing
the order of the remaining elements. For example, [3,6,2,7]
is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1*/

package com.practice.code.dynamicprogramming;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence implements CodeRunner {
    @Override
    public void run() {

    }

    // dp
    public int lengthOfLISDP(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int longest = 0;
        for (int c: dp) {
            longest = Math.max(longest, c);
        }

        return longest;
    }

    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> sub = new ArrayList<>();
        sub.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num > sub.get(sub.size() - 1)) {
                sub.add(num);
            } else {
                int j = binarySearch(sub, num);
                sub.set(j, num);
            }
        }

        return sub.size();
    }

//    Initialize an array sub which contains the first element of nums.
//    Iterate through the input, starting from the second element. For each element num:
//        If num is greater than any element in sub, then add num to sub.
//            Otherwise, perform a binary search in sub to find the smallest element that is greater than or equal to num. Replace that element with num.
//    Return the length of sub.
    // binary search
    private int binarySearch(ArrayList<Integer> sub, int num) {
        int left = 0;
        int right = sub.size() - 1;
        int mid = (left + right) / 2;

        while (left < right) {
            mid = (left + right) / 2;
            if (sub.get(mid) == num) {
                return mid;
            }

            if (sub.get(mid) < num) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
