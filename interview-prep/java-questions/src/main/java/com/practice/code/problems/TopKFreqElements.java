/*Given an integer array nums and an integer k,
return the k most frequent elements. You may return the answer in any order.

Example 1:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2

Example 2:
Input: nums = [1], k = 1
Output: [1]
*/

package com.practice.code.problems;

import com.practice.code.runner.CodeRunner;

import java.util.*;

//The first step is to build a hash map element -> its frequency.
// In Java, we use the data structure HashMap. Python provides dictionary
// subclass Counter to initialize the hash map we need directly from the input array.
//This step takes \mathcal{O}(N)O(N) time where N is a number of elements in the list.
//The second step is to build a heap of size k using N elements. To add the first k
// elements takes a linear time \mathcal{O}(k)O(k) in the average case,
// and \mathcal{O}(\log 1 + \log 2 + ... + \log k) = \mathcal{O}(log k!) = \mathcal{O}(k \log k)O(log1+log2+...+logk)=O(logk!)=O(klogk)
// in the worst case. It's equivalent to heapify implementation in Python. After the first k elements we start to push and pop
// at each step, N - k steps in total. The time complexity of heap push/pop is \mathcal{O}(\log k)O(logk) and
// we do it N - k times that means \mathcal{O}((N - k)\log k)O((N−k)logk) time complexity. Adding both parts up,
// we get \mathcal{O}(N \log k)O(Nlogk) time complexity for the second step.
//The third and the last step is to convert the heap into an output array.
// That could be done in \mathcal{O}(k \log k)O(klogk) time.
public class TopKFreqElements implements CodeRunner {
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

    @Override
    public void run() {

    }

    // QUICKSELECT
    int[] unique;
    Map<Integer, Integer> count;

    public void swap(int a, int b) {
        int tmp = unique[a];
        unique[a] = unique[b];
        unique[b] = tmp;
    }

    public int partition(int left, int right, int pivot_index) {
        int pivot_frequency = count.get(unique[pivot_index]);
        // 1. move pivot to end
        swap(pivot_index, right);
        int store_index = left;

        // 2. move all less frequent elements to the left
        for (int i = left; i <= right; i++) {
            if (count.get(unique[i]) < pivot_frequency) {
                swap(store_index, i);
                store_index++;
            }
        }

        // 3. move pivot to its final place
        swap(store_index, right);

        return store_index;
    }

    public void quickselect(int left, int right, int k_smallest) {
        /*
        Sort a list within left..right till kth less frequent element
        takes its place.
        */

        // base case: the list contains only one element
        if (left == right) return;

        // select a random pivot_index
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);

        // find the pivot position in a sorted list
        pivot_index = partition(left, right, pivot_index);

        // if the pivot is in its final sorted position
        if (k_smallest == pivot_index) {
            return;
        } else if (k_smallest < pivot_index) {
            // go left
            quickselect(left, pivot_index - 1, k_smallest);
        } else {
            // go right
            quickselect(pivot_index + 1, right, k_smallest);
        }
    }

    public int[] topKFrequentQuick(int[] nums, int k) {
        // build hash map : character and how often it appears
        count = new HashMap();
        for (int num: nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }

        // array of unique elements
        int n = count.size();
        unique = new int[n];
        int i = 0;
        for (int num: count.keySet()) {
            unique[i] = num;
            i++;
        }

        // kth top frequent element is (n - k)th less frequent.
        // Do a partial sort: from less frequent to the most frequent, till
        // (n - k)th less frequent element takes its place (n - k) in a sorted array.
        // All element on the left are less frequent.
        // All the elements on the right are more frequent.
        quickselect(0, n - 1, n - k);
        // Return top k frequent elements
        return Arrays.copyOfRange(unique, n - k, n);
    }
}