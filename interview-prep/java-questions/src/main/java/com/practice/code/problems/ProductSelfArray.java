/*Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

You must write an algorithm that runs in O(n) time and without using the division operation.

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]*/

package com.practice.code.problems;

class ProductSelfArray {
    // Intuitive way of building left and right arrays to produce a product array using left[i]*right[i]
    // Time: O(n) Space: O(n)
    public int[] productExceptSelfN(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        left[0] = 1;
        right[right.length-1] = 1;
        // fill left array
        for(int i = 1; i < left.length; i++){
            left[i] = left[i-1]*nums[i-1];
        }
        // fill right array
        for(int i = right.length-2; i >= 0; i--){
            right[i] = right[i+1]*nums[i+1];
        }
        // now fill product array using left[i]*right[i]
        int[] product = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            product[i] = left[i]*right[i];
        }
        return product;
    }
    public int[] productExceptSelf(int[] nums) {
        int[] ret = new int[nums.length];
        int tmp = 1;
        // nums: [1, 3, 4] | [1, 0] | [2, 5, 6, 10]
        for(int i = 0; i < nums.length; i++){
            ret[i] = tmp;  
            tmp *= nums[i]; 
        }
        // ret: [1, 1, 3] | [1, 1] | [1, 2, 10, 60]
        tmp = 1;
        for(int j = nums.length-1; j > -1; j--){
            ret[j] *= tmp;
            tmp *= nums[j]; // this is important for second step
        }
        return ret;
    }
}