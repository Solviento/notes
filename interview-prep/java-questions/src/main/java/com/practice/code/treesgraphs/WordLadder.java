/*A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence
from beginWord to endWord, or 0 if no such sequence exists.

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
*/

package com.practice.code.treesgraphs;

import com.practice.code.runner.CodeRunner;

import java.util.*;

public class WordLadder implements CodeRunner {

    @Override
    public void run() {

    }

    public int ladderLength1(String beginWord, String endWord, Set<String> wordDict) {
        Set<String> reached = new HashSet<String>();
        reached.add(beginWord);
        wordDict.add(endWord);
        int distance = 1;
        while (!reached.contains(endWord)) {
            Set<String> toAdd = new HashSet<String>();
            for (String each : reached) {
                for (int i = 0; i < each.length(); i++) {
                    char[] chars = each.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        chars[i] = ch;
                        String word = new String(chars);
                        if (wordDict.contains(word)) {
                            toAdd.add(word);
                            wordDict.remove(word);
                        }
                    }
                }
            }
            distance++;
            if (toAdd.size() == 0) return 0;
            reached = toAdd;
        }
        return distance;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.add(beginWord);
        // COUNT NUMBER OF WORDS TRANSFORMED
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // FOR ALL WORDS THIS ROUND
            for (int i = 0; i < size; i++) {
                char[] current = queue.poll().toCharArray();
                // TRAVERSE CURRENT WORD
                for (int j = 0; j < current.length; j++) {
                    char tmp = current[j];
                    // CHANGE ONE LETTER AT A TIME
                    for (char c = 'a'; c <= 'z'; c++) {
                        current[j] = c;
                        String next = new String(current);
                        // WHEN NEXT WORD IS IN THE SET
                        if (set.contains(next)) {
                            if (next.equals(endWord)) return count + 1;
                            queue.add(next);
                            set.remove(next);
                        }
                    }
                    // HAVE TO UNDO FOR NEXT CHANGE OF LETTER
                    current[j] = tmp;
                }
            }
            // THIS ROUND ENDS WITH ONE LETTER CHANGED
            count++;
        }
        return 0;
    }


}
