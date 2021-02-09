package com.practice.code.problems;
class MergeSortedArray {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    // 1 2 3 0 0 0
    //     lP    rP
    // 2 5 6
    //     tP
    int lPointer = m - 1;
    int rPointer = m + n - 1;
    int thirdPointer = n - 1;
    while (thirdPointer >= 0 && lPointer >= 0) {
      int n2 = nums2[thirdPointer];
      int n1 = nums1[lPointer];
      if (n2 >= n1) {
        nums1[rPointer] = n2;
        thirdPointer--;
        rPointer--;
      } else {
        nums1[rPointer] = n1;
        nums1[lPointer] = n2;
        rPointer--;
        lPointer--;
      }
    }
    // if nums1 array is empty (no elements), we just fill in with nums2 arr
    while (thirdPointer >= 0) {
      nums1[thirdPointer] = nums2[thirdPointer];
      thirdPointer--;
    }
  }
}