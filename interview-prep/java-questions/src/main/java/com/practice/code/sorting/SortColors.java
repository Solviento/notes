/*Given an array nums with n objects colored red,
white, or blue, sort them in-place so that objects
of the same color are adjacent, with the colors in
the order red, white, and blue.
We will use the integers 0, 1, and 2 to represent
the color red, white, and blue, respectively.
You must solve this problem without using the library's sort function.

Example 1:
Input: nums = [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Example 2:
Input: nums = [2,0,1]
Output: [0,1,2]*/
package com.practice.code.sorting;

import com.practice.code.runner.CodeRunner;

public class SortColors implements CodeRunner {
    @Override
    public void run() {

    }

    // 3 pointers solution
    // only 0, 1, 2 in array -> use this property to sort in place
    // [2  0  1]
    //  S     E
    //  C
    // keep swapping elements until elements are sorted from 0's to 2's
    public void sortColors(int[] nums) {
        int startIndex = 0;
        int currentIndex = 0;
        int endIndex = nums.length - 1;
        int tmp;
        while (currentIndex <= endIndex) {
            if (nums[currentIndex] == 0) {
                tmp = nums[startIndex];
                nums[startIndex++] = nums[currentIndex];
                nums[currentIndex++] = tmp;
            }
            else if (nums[currentIndex] == 2) {
                tmp = nums[currentIndex];
                nums[currentIndex] = nums[endIndex];
                nums[endIndex--] = tmp;
            }
            else currentIndex++;
        }
    }
}
