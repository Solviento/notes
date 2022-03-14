package com.practice.code.searching;

import com.practice.code.runner.CodeRunner;

public class BinarySearchIterative implements CodeRunner {

    // Returns index of x if it is present in arr[],
    // else return -1
    int binarySearch(int[] arr, int target) {
        int leftIndex = 0, rightIndex = arr.length - 1;
        while (leftIndex <= rightIndex) {
            int midIndex = leftIndex + (rightIndex - leftIndex) / 2;
            // Check if x is present at mid
            if (arr[midIndex] == target)
                return midIndex;
            // If x greater, ignore left half
            if (arr[midIndex] < target)
                leftIndex = midIndex + 1;
                // If x is smaller, ignore right half
            else
                rightIndex = midIndex - 1;
        }
        // if we reach here, then element was
        // not present
        return -1;
    }

    @Override
    public void run() {
        BinarySearchIterative ob = new BinarySearchIterative();
        int[] arr = {2, 3, 4, 10, 40};
        int n = arr.length;
        int x = 10;
        int result = ob.binarySearch(arr, x);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at "
                    + "index " + result);
    }
}
