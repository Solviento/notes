/*Let's call an array arr a mountain if the following properties hold:
arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... arr[i-1] < arr[i]
arr[i] > arr[i+1] > ... > arr[arr.length - 1]
Given an integer array arr that is guaranteed to be a mountain, return any i such that
arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].

Example 1:
Input: arr = [0,1,0]
Output: 1

Example 2:
Input: arr = [0,2,1,0]
Output: 1

Example 3:
Input: arr = [0,10,5,2]
Output: 1*/
package com.practice.code.sorting;

import com.practice.code.runner.CodeRunner;

public class PeakIndexInAMountainArray implements CodeRunner {
    @Override
    public void run() {

    }

    public int peakIndexInMountainArray(int[] A) {
        int index = 0;
        while (A[index] < A[index + 1]) {
            index++;
        }
        return index;
    }

    public int peakIndexInMountainArrayBinarySearch(int[] A) {
        int startIndex = 0;
        int endIndex = A.length - 1;
        while (startIndex < endIndex) {
            int midIndex = startIndex + (endIndex - startIndex) / 2;
            if (A[midIndex] < A[midIndex + 1]) {
                startIndex = midIndex + 1;
            } else {
                endIndex = midIndex;
            }
        }
        return startIndex;
    }
}
