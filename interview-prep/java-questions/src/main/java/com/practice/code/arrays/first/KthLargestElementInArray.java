/*Given an integer array nums and an integer k, return the kth largest element in the array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:
Input: nums = [3,2,1,5,6,4], k = 2
Output: 5

Example 2:
Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
*/
package com.practice.code.arrays.first;

import com.practice.code.runner.CodeRunner;

import java.util.PriorityQueue;

public class KthLargestElementInArray implements CodeRunner {

    @Override
    public void run() {

    }

    // maintain heap of size k, by maintaining heap we cull out smallest values and retain only top K valued elements
    public int findKthLargest(int[] nums, int k) {
        // max heap initialization
        PriorityQueue<Integer> heap =
                new PriorityQueue<>((x, y) -> y - x);

        // keep ONLY k largest elements in the heap
        // max heap will bubble kth largest element to the top, all lesser values will be below
        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // return head of max heap, which is kth largest element
        return heap.poll();
    }
}
