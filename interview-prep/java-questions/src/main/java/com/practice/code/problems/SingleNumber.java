/*Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:

Input: nums = [2,2,1]
Output: 1*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

class SingleNumber implements CodeRunner {
    static int singleNumber(int[] nums) {
        // a XOR 0 = a    DO THE BIT MATH CALCULATIONS BY HAND TO VERIFY
        // a XOR a = 0
        // a XOR b XOR a = a XOR a XOR b = 0 XOR b = b
        int rem = 0;
        for (Integer e : nums) {
            rem = rem ^ e;  // ALSO DO THESE BIT CALCULATIONS TO SEE WHY XOR WORKS AND & + | DONT
        }
        return rem;
    }

    @Override
    public void run() {
        // int[] nums = new int[]{1,1,2,3,3};
        int[] nums = new int[]{1, 2};
        int uni = singleNumber(nums);
        System.out.println(uni);
    }
}