/*Given an array of strings words and an integer k,
return the k most frequent strings.
Return the answer sorted by the frequency from
highest to lowest. Sort the words with the same frequency by their lexicographical order.

Example 1:

Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:

Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.*/
package com.practice.code.arraysandstrings;

import com.practice.code.runner.CodeRunner;

import java.util.*;

public class TopKFrequentWords implements CodeRunner {
    @Override
    public void run() {

    }

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(new MyComparator());
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            pq.offer(e);
        }
        List<String> ans = new LinkedList<>();
        for (int i = 0; i <= k - 1; i++) {
            ans.add(pq.poll().getKey());
        }
        return ans;
    }

    class MyComparator implements Comparator<Map.Entry<String, Integer>> {
        public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
            String word1 = e1.getKey();
            int freq1 = e1.getValue();
            String word2 = e2.getKey();
            int freq2 = e2.getValue();
            if (freq1 != freq2) {
                return freq2 - freq1;
            } else {
                return word1.compareTo(word2);
            }
        }
    }


}
