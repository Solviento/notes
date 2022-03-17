package com.practice.code.searching;

import com.practice.code.runner.CodeRunner;

public class FindFirstAndLastPositionOfElementInSortedArray implements CodeRunner {
    @Override
    public void run() {
        int[] arr = {5,7,7,7,7,10}; // 1 -> 4
        int[] out = searchRange(arr, 7);
        for(int e: out) {
            System.out.print(e + " ");
        }
    }

    public int[] searchRange(int[] nums, int target) {
        int firstOccurrence = this.findBoundLeft(nums, target);
        if (firstOccurrence == -1) {
            return new int[]{-1, -1};
        }

        int lastOccurrence = this.findBoundRight(nums, target);
        return new int[]{firstOccurrence, lastOccurrence};
    }

    private int findBoundLeft(int[] nums, int target) {
        int N = nums.length;
        int begin = 0, end = N - 1;

        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (nums[mid] == target) {
                // This means we found our lower bound.
                // mid == begin means that mid pointer has reached beginning of sub array
                // while nums[mid-1] != target is checking if found element is also preceding current element
                if (mid == begin || nums[mid - 1] != target) {
                    return mid;
                }
                // Search on the left side for the bound.
                end = mid - 1;

            } else if (nums[mid] > target) {
                // element found too big, search left subarray
                end = mid - 1;
            } else {
                // element found too small, search right subarray
                begin = mid + 1;
            }
        }
        return -1;
    }

    private int findBoundRight(int[] nums, int target) {
        int N = nums.length;
        int begin = 0, end = N - 1;

        while (begin <= end) {
            int mid = (begin + end) / 2;
            if (nums[mid] == target) {
                // This means we found our upper bound.
                if (mid == end || nums[mid + 1] != target) {
                    return mid;
                }
                // Search on the right side for the bound.
                begin = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }
        return -1;
    }
}
