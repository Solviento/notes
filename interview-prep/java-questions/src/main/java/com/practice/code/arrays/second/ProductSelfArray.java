/*Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]*/

package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

class ProductSelfArray implements CodeRunner {

    // Time: O(n) Space: O(1)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // Calculate lefts and store in res.
        int left = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0)
                left = left * nums[i - 1];
            res[i] = left;
        }
        // Calculate rights and the product from the end of the array.
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1)
                right = right * nums[i + 1];
            res[i] *= right;
        }
        return res;
    }
    
    // Intuitive way of building left and right arrays to produce a product array using left[i]*right[i]
    // Time: O(n) Space: O(n)
    public int[] productExceptSelfN(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        right[right.length - 1] = 1;
        // fill left array
        for (int i = 1; i < left.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }
        // fill right array
        for (int i = right.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }
        // now fill product array using left[i]*right[i]
        int[] product = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            product[i] = left[i] * right[i];
        }
        return product;
    }

    @Override
    public void run() {

    }
}