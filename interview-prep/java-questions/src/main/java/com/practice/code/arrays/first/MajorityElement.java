/*Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:

Input: nums = [3,2,3]
Output: 3
*/

package com.practice.code.arrays.first;

import java.util.HashMap;

public class MajorityElement {
    static int majorityElement(int[] nums) {
        HashMap<Integer, Integer> maj = new HashMap<>();
        for (Integer e : nums) {
            if (!maj.containsKey(e)) {
                maj.put(e, 1);
            } else {
                int c = maj.get(e);
                maj.put(e, c + 1);
                // threshold for majority
                if (maj.get(e) > (nums.length / 2)) {
                    return e;
                }
            }
        }
        // if array is one element, return element
        return nums[0];
    }
}