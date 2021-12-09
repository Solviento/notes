package com.practice.code.problems;
public class RemoveDuplicates {
  public int removeDuplicates(int[] nums) {
    // use two pointers, one slow one fast
    int slow = 0;
    for (int fast = 0; fast < nums.length; fast++) {
      if (nums[slow] != nums[fast]) {
        slow++;
        nums[slow] = nums[fast];
      } else {
        // slow remains unchanged, fast is incremented
        continue;
      }
    }
    // slow also serves as a counter to how many 'duplicates' we found
    return slow + 1; // slow + 1 = length of array with non duplicates
  }
}