/*Given an array arr[] of length N and an integer X, the task is to find the number of subsets with a sum equal to X using recursion.
Examples:


Input: arr[] = {2, 3, 5, 6, 8, 10}, X = 10
Output: 3
Explanation:
All possible subsets with sum 10 are {2, 3, 5}, {2, 8}, {10}
Input: arr[] = {1, 2, 3, 4, 5}, X = 7
Output: 3
Explanation:
All possible subsets with sum 7 are {2, 5}, {3, 4}, {1, 2, 4} */
package com.practice.code.recursion;

import com.practice.code.runner.CodeRunner;

public class SubsetSum implements CodeRunner {
    @Override
    public void run() {

    }

    // Recursive function to return the count
    // of subsets with sum equal to the given value
    static int subsetSum(int arr[], int n, int sum, int s,
                         int count)
    {

        // The recursion is stopped at N-th level
        // where all the subsets of the given array
        // have been checked
        if (n == 0) {

            // Incrementing the count if sum is
            // equal to the subset and returning the count
            if (sum == s) {
                count++;
            }
            return count;
        }

        count = subsetSum(arr, n - 1, sum, s, count);
        count = subsetSum(arr, n - 1, sum, s + arr[n - 1],
                count);
        return count;
    }

    // Returns true if there exists a subsequence of `A[0…n]` with the given sum
    public static boolean subsetSum(int[] A, int n, int k)
    {
        // return true if the sum becomes 0 (subset found)
        if (k == 0) {
            return true;
        }

        // base case: no items left, or sum becomes negative
        if (n < 0 || k < 0) {
            return false;
        }

        // Case 1. Include the current item `A[n]` in the subset and recur
        // for the remaining items `n-1` with the remaining total `k-A[n]`
        boolean include = subsetSum(A, n - 1, k - A[n]);

        // Case 2. Exclude the current item `A[n]` from the subset and recur for
        // the remaining items `n-1`
        boolean exclude = subsetSum(A, n - 1, k);

        // return true if we can get subset by including or excluding the
        // current item
        return include || exclude;
    }
}
