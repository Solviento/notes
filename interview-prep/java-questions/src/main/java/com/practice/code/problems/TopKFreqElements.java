/*Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2*/

package com.practice.code.problems;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class TopKFreqElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        // build hash map, integer: frequency
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer e : nums) {
            map.put(e, map.getOrDefault(e, 0) + 1);
        }
        // initialize the heap
        PriorityQueue<Integer> heap = new PriorityQueue<>((x, y) -> map.get(x) - map.get(y));
        for (Integer e : map.keySet()) {
            heap.add(e);    // max heapify
            // remove extra elements once heap has size greater than k elements
            if (heap.size() > k) {
                int n = heap.poll();
            }
        }
        List<Integer> topKElements = new LinkedList<>();
        while (!heap.isEmpty()) {
            int e = heap.poll();
            topKElements.add(e);
        }
        Collections.reverse(topKElements);
        return topKElements;
    }
}