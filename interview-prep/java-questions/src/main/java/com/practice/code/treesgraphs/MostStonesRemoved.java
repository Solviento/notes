package com.practice.code.treesgraphs;

import com.practice.code.runner.CodeRunner;

import java.util.*;

public class MostStonesRemoved implements CodeRunner {
    @Override
    public void run() {

    }

    // Compute connected components with DFSes that only iterate neighbours of the current node.
    public int removeStonesSecond(int[][] stones) {
        Map<Integer, List<Integer>> rowToStones = new HashMap<>();      // Maps a row to all stones in that row. We represent a stone with an index in the stones array.
        Map<Integer, List<Integer>> colToStones = new HashMap<>();      // Maps a column to all stones in that column.
        for (int i = 0; i < stones.length; ++i) {
            rowToStones.putIfAbsent(stones[i][0], new ArrayList<>());
            rowToStones.get(stones[i][0]).add(i);  // Add stone i to row stone[i][0].
            colToStones.putIfAbsent(stones[i][1], new ArrayList<>());
            colToStones.get(stones[i][1]).add(i);  // Add stone i to column stone[i][1].
        }

        Set<Integer> seen = new HashSet<>();      // Store all the stones that have already been visited.
        int numComponents = 0;
        for (int i = 0; i < stones.length; ++i) {
            if (!seen.contains(i)) {
                numComponents++;
                seen.add(i);
                dfs(stones, rowToStones, colToStones, seen, i);     // Mark all stones in this connected component (all reachable stones) as seen.
            }
        }
        return stones.length - numComponents;
    }

    // For currStone, iterate over all stones in the same row and same column, recurse on the unseen ones (mark them as seen and explore stones that they can reach).
    private void dfs(int[][] stones, Map<Integer, List<Integer>> rowToStones, Map<Integer, List<Integer>> colToStones, Set<Integer> seen, int currStone) {
        assert (0 <= currStone && currStone < stones.length && seen.contains(currStone)) : "We represent stones as indices in the given stones array.";

        // Iterate through all stones in the same row as currStone and process (mark as seen and recurse) the unseen ones.
        for (int stoneInSameRow : rowToStones.getOrDefault(stones[currStone][0], new ArrayList<>())) {
            if (!seen.contains(stoneInSameRow)) {
                seen.add(stoneInSameRow);
                dfs(stones, rowToStones, colToStones, seen, stoneInSameRow);
            }
        }

        for (int stoneInSameCol : colToStones.getOrDefault(stones[currStone][1], new ArrayList<>())) {
            if (!seen.contains(stoneInSameCol)) {
                seen.add(stoneInSameCol);
                dfs(stones, rowToStones, colToStones, seen, stoneInSameCol);
            }
        }
    }
}
