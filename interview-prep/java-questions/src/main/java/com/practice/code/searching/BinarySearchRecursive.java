package com.practice.code.searching;

import com.practice.code.runner.CodeRunner;

public class BinarySearchRecursive implements CodeRunner {

    // t: o(logn) s: o(logn)
    int binarySearch(int[] arr, int leftIndex, int rightIndex, int target) {
        if (rightIndex >= leftIndex) {
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            // If the element is present at the
            // middle itself
            if (arr[mid] == target)
                return mid;
            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > target)
                return binarySearch(arr, leftIndex, mid - 1, target);
            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, rightIndex, target);
        }
        // We reach here when element is not present
        // in array
        return -1;
    }

    @Override
    public void run() {
        BinarySearchRecursive ob = new BinarySearchRecursive();
        int arr[] = {2, 3, 4, 10, 40};
        int n = arr.length;
        int x = 10;
        int result = ob.binarySearch(arr, 0, n - 1, x);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at index "
                    + result);
    }
}
