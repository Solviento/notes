/*A transformation sequence from word beginWord to
word endWord using a dictionary wordList is a sequence
of words beginWord -> s1 -> s2 -> ... -> sk such that:
Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary
wordList, return all the shortest transformation sequences
from beginWord to endWord, or an empty list if no such
sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

Example 1:
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"

Example 2
Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.*/

package com.practice.code.trees;

import com.practice.code.runner.CodeRunner;

import java.util.*;

public class WordLadderII implements CodeRunner {
    @Override
    public void run() {

    }

    // bfs + backtracking
    Map<String, List<String>> adjList = new HashMap<String, List<String>>();
    List<String> currPath = new ArrayList<String>();
    List<List<String>> shortestPaths = new ArrayList<List<String>>();

    private List<String> findNeighbors(String word, Set<String> wordList) {
        List<String> neighbors = new ArrayList<String>();
        char charList[] = word.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            char oldChar = charList[i];

            // replace the i-th character with all letters from a to z except the original character
            for (char c = 'a'; c <= 'z'; c++) {
                charList[i] = c;

                // skip if the character is same as original or if the word is not present in the wordList
                if (c == oldChar || !wordList.contains(String.valueOf(charList))) {
                    continue;
                }
                neighbors.add(String.valueOf(charList));
            }
            charList[i] = oldChar;
        }
        return neighbors;
    }

    private void backtrack(String source, String destination) {
        // store the path if we reached the endWord
        if (source.equals(destination)) {
            List<String> tempPath = new ArrayList<String>(currPath);
            shortestPaths.add(tempPath);
        }

        if (!adjList.containsKey(source)) {
            return;
        }

        for (int i = 0; i < adjList.get(source).size(); i++) {
            currPath.add(adjList.get(source).get(i));
            backtrack(adjList.get(source).get(i), destination);
            currPath.remove(currPath.size() - 1);
        }
    }

    private void bfs(String beginWord, String endWord, Set<String> wordList) {
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);

        // remove the root word which is the first layer in the BFS
        if (wordList.contains(beginWord)) {
            wordList.remove(beginWord);
        }

        Map<String, Integer> isEnqueued = new HashMap<String, Integer>();
        isEnqueued.put(beginWord, 1);

        while (q.size() > 0)  {
            // visited will store the words of current layer
            List<String> visited = new ArrayList<String>();

            for (int i = q.size() - 1; i >= 0; i--) {
                String currWord = q.peek();
                q.remove();

                // findNeighbors will have the adjacent words of the currWord
                List<String> neighbors = findNeighbors(currWord, wordList);
                for (String word : neighbors) {
                    visited.add(word);

                    if (!adjList.containsKey(currWord)) {
                        adjList.put(currWord, new ArrayList<String>());
                    }

                    // add the edge from currWord to word in the list
                    adjList.get(currWord).add(word);
                    if (!isEnqueued.containsKey(word)) {
                        q.add(word);
                        isEnqueued.put(word, 1);
                    }
                }
            }
            // removing the words of the previous layer
            for (int i = 0; i < visited.size(); i++) {
                if (wordList.contains(visited.get(i))) {
                    wordList.remove(visited.get(i));
                }
            }
        }
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // copying the words into the set for efficient deletion in BFS
        Set<String> copiedWordList = new HashSet<>(wordList);
        // build the DAG using BFS
        bfs(beginWord, endWord, copiedWordList);

        // every path will start from the beginWord
        currPath.add(beginWord);
        // traverse the DAG to find all the paths between beginWord and endWord
        backtrack(beginWord, endWord);

        return shortestPaths;
    }

    // bidirectional bfs + backtracking
//    Map<String, List<String>> adjList = new HashMap<String, List<String>>();
//    List<String> currPath = new ArrayList<String>();
//    List<List<String>> shortestPaths = new ArrayList<List<String>>();
//
//    private List<String> findNeighborsBi(String word, Set<String> wordList) {
//        List<String> neighbors = new ArrayList<String>();
//        char charList[] = word.toCharArray();
//
//        for (int i = 0; i < word.length(); i++) {
//            char oldChar = charList[i];
//
//            // replace the i-th character with all letters from a to z except the original character
//            for (char c = 'a'; c <= 'z'; c++) {
//                charList[i] = c;
//
//                // skip if the character is same as original or if the word is not present in the wordList
//                if (c == oldChar || !wordList.contains(String.valueOf(charList))) {
//                    continue;
//                }
//                neighbors.add(String.valueOf(charList));
//            }
//            charList[i] = oldChar;
//        }
//        return neighbors;
//    }
//
//    private void backtrackBi(String source, String destination) {
//        //System.out.printlen(source);
//        // store the path if we reached the endWord
//        if (source.equals(destination)) {
//            List<String> tempPath = new ArrayList<String>(currPath);
//            shortestPaths.add(tempPath);
//        }
//
//        if (!adjList.containsKey(source)) {
//            return;
//        }
//
//        for (int i = 0; i < adjList.get(source).size(); i++) {
//            currPath.add(adjList.get(source).get(i));
//            backtrackBi(adjList.get(source).get(i), destination);
//            currPath.remove(currPath.size() - 1);
//        }
//    }
//
//    private void swap(Set<String> forward, Set<String> backward) {
//        Set<String> temp = forward;
//        forward = backward;
//        backward = temp;
//    }
//    private void addEdge(String word1, String word2, int direction) {
//        if(direction == 1) {
//            if (!adjList.containsKey(word1)) {
//                adjList.put(word1, new ArrayList<String>());
//            }
//            adjList.get(word1).add(word2);
//        } else {
//            if (!adjList.containsKey(word2)) {
//                adjList.put(word2, new ArrayList<String>());
//            }
//            adjList.get(word2).add(word1);
//        }
//    }
//
//    private boolean Bibfs(String beginWord, String endWord, Set<String> wordList) {
//        if (wordList.contains(endWord) == false) {
//            return false;
//        }
//
//        // remove the root word which is the first layer
//        if (wordList.contains(beginWord)) {
//            wordList.remove(beginWord);
//        }
//
//        Set<String> forwardQueue =  new HashSet<String>();
//        Set<String> backwardQueue = new HashSet<String>();
//
//        forwardQueue.add(beginWord);
//        backwardQueue.add(endWord);
//
//        boolean found = false;
//        int direction = 1;
//
//        while (forwardQueue.isEmpty() != true)  {
//            // visited will store the words of current layer
//            Set<String> visited = new HashSet<String>();
//
//            // swap the queues because we are always extending the forwardQueue
//            if (forwardQueue.size() > backwardQueue.size()) {
//                Set<String> temp = forwardQueue;
//                forwardQueue = backwardQueue;
//                backwardQueue = temp;
//                direction ^= 1;
//            }
//
//            for (String currWord : forwardQueue) {
//                List<String> neighbors = findNeighbors(currWord, wordList);
//                for (String word : neighbors) {
//
//                    // if the backwardQueue already contains it we can stop after completing this level
//                    if (backwardQueue.contains(word)) {
//                        found = true;
//                        addEdge(currWord, word, direction);
//                    }
//
//                     /* the word shouldn't be presnt in forwardQueue because if it is then the edge will
//                     be between two words at the same level which we don't want */
//                    else if (!found && wordList.contains(word) == true && forwardQueue.contains(word) == false) {
//                        visited.add(word);
//                        addEdge(currWord, word, direction);
//                    }
//                }
//            }
//
//            // removing the words of the previous layer
//            for (String currWord : forwardQueue) {
//                if (wordList.contains(currWord)) {
//                    wordList.remove(currWord);
//                }
//            }
//
//            if (found) {
//                break;
//            }
//
//            forwardQueue = visited;
//        }
//        return found;
//    }
//
//    public List<List<String>> findLaddersBi(String beginWord, String endWord, List<String> wordList) {
//        // copying the words into the set for efficient deletion in BFS
//        Set<String> copiedWordList = new HashSet<>(wordList);
//        // build the DAG using BFS
//        boolean sequence_found = Bibfs(beginWord, endWord, copiedWordList);
//
//        // There is no valid sequence that connects `beginWord` to `endWord`
//        if (sequence_found == false) {
//            return shortestPaths;
//        }
//        // every path will start from the beginWord
//        currPath.add(beginWord);
//        // traverse the DAG to find all the paths between beginWord and endWord
//        backtrack(beginWord, endWord);
//
//        return shortestPaths;
//    }
}
