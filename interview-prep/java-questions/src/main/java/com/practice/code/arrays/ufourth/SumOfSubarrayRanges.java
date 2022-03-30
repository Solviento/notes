/*You are given an integer array nums. The range of a
subarray of nums is the difference between the largest
and smallest element in the subarray.
Return the sum of all subarray ranges of nums.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,2,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[2], range = 2 - 2 = 0
[3], range = 3 - 3 = 0
[1,2], range = 2 - 1 = 1
[2,3], range = 3 - 2 = 1
[1,2,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.

Example 2:
Input: nums = [1,3,3]
Output: 4
Explanation: The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[3], range = 3 - 3 = 0
[3], range = 3 - 3 = 0
[1,3], range = 3 - 1 = 2
[3,3], range = 3 - 3 = 0
[1,3,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.

Example 3:
Input: nums = [4,-2,-3,4,1]
Output: 59
Explanation: The sum of all subarray ranges of nums is 59.*/

package com.practice.code.arrays.ufourth;

import com.practice.code.runner.CodeRunner;

import java.util.ArrayDeque;
import java.util.Deque;

public class SumOfSubarrayRanges implements CodeRunner {
    @Override
    public void run() {

    }

    // t: n o: n
    public long subArrayRanges(int[] nums) {

        Deque<Integer> minStack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();
        long result = 0;
        minStack.offerFirst(-1);
        maxStack.offerFirst(-1);
        for(int i=0; i<=nums.length; i++) {
            while(minStack.peekFirst() != -1 && (i == nums.length || nums[i] < nums[minStack.peekFirst()])) {
                result -= calcRange(minStack, i, nums);
            }
            minStack.offerFirst(i);
            while (maxStack.peekFirst() != -1  && (i == nums.length || nums[i] > nums[maxStack.peekFirst()])) {
                result += calcRange(maxStack, i, nums);
            }
            maxStack.offerFirst(i);
        }
        return result;
    }
    private long calcRange(Deque<Integer> stack , int i, int[] nums) {
        int index = stack.pollFirst();
        int left = index - stack.peekFirst();
        int right = i - index;
        return ((left * right) * (long)nums[index]);
    }
}
