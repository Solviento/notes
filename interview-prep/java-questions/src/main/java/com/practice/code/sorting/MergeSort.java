package com.practice.code.sorting;

import com.practice.code.runner.CodeRunner;

import java.util.Arrays;

public class MergeSort implements CodeRunner {

    @Override
    public void run() {
        int[] arr = { 12, 11, 13, 5, 6, 7 };
        System.out.println("Given Array " + Arrays.toString(arr));
        sort(arr, 0, arr.length - 1);
        System.out.println("\nSorted array " + Arrays.toString(arr));
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void sort(int[] arr, int startIndex, int endIndex)
    {
        if (startIndex < endIndex) {
            // Find the middle point
            int m =startIndex+ (endIndex-startIndex)/2;
            // Sort first and second halves
            sort(arr, startIndex, m);
            sort(arr, m + 1, endIndex);
            // Merge the sorted halves
            merge(arr, startIndex, m, endIndex);
        }
    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int[] arr, int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
        /* Create temp arrays */
        int[] L = new int[n1];
        int[] R = new int[n2];
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
        /* Merge the temp arrays */
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
