/*You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix. In this matrix:
0 means the cell cannot be walked through.
1 represents an empty cell that can be walked through.
A number greater than 1 represents a tree in a cell
that can be walked through, and this number is the tree's height.
In one step, you can walk in any of the four directions:
north, east, south, and west. If you are standing in a cell with a tree, you can choose whether to cut it off.
You must cut off the trees in order from shortest to tallest.
When you cut off a tree, the value at its cell becomes 1 (an empty cell).
Starting from the point (0, 0), return the minimum steps you
need to walk to cut off all the trees. If you cannot cut off all the trees, return -1.
You are guaranteed that no two trees have the same height,
and there is at least one tree needs to be cut off.

Example 1
Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
Output: 6
Explanation: Following the path above allows you to cut off the trees from shortest to tallest in 6 steps.

Example 2:
Input: forest = [[1,2,3],[0,0,0],[7,6,5]]
Output: -1
Explanation: The trees in the bottom row cannot be accessed as the middle row is blocked.

Example 3:
Input: forest = [[2,3,4],[0,0,5],[8,7,6]]
Output: 6
Explanation: You can follow the same path as Example 1 to cut off all the trees.
Note that you can cut off the first tree at (0, 0) before making any steps.*/

package com.practice.code.graphs;

import com.practice.code.runner.CodeRunner;

import java.util.*;

public class CutOffTressForGolfEvent implements CodeRunner {
    @Override
    public void run() {

    }

    //    Logical Thinking
//    Since we always cut off the tree with lowest height first, we collect all trees and sort them by heights.
//    If we make sure we walk the minimum steps to cut off each tree, the sum of these minimum steps will be the final answer.
//    For cutting each tree (aimX, aimY), we implement BFS once:
//    Start point: (curX, curY)
//    Aim point: (aimX, aimY)
//    End point: (aimX, aimY) or we have no way out.
//    Transition: we try advancing by one step in 4 directions.
    private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int cutOffTree(List<List<Integer>> forest) {

        List<int[]> treeHeights = getAllTreeHights(forest);
        Collections.sort(treeHeights, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        int minSteps = 0;
        int curX = 0, curY = 0;
        while (!treeHeights.isEmpty()) {
            int[] curTree = treeHeights.remove(0);
            int steps = getMinimumSteps(forest, curX, curY, curTree[0], curTree[1]);
            if (steps == -1) {
                return -1;
            }
            minSteps += steps;
            curX = curTree[0];
            curY = curTree[1];
            forest.get(curX).set(curY, 1);
        }
        return minSteps;
    }

    private int getMinimumSteps(List<List<Integer>> forest, int curX, int curY, int aimX, int aimY) {

        int minSteps = 0;
        int rows = forest.size(), cols = forest.get(0).size();
        boolean[][] visited = new boolean[forest.size()][forest.get(0).size()];
        Queue<int[]> queue = new LinkedList<>();
        int startVal = forest.get(curX).get(curY);
        queue.offer(new int[]{curX, curY});
        visited[curX][curY] = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curCell = queue.poll();
                if (curCell[0] == aimX && curCell[1] == aimY) {
                    return minSteps;
                }
                for (int[] direction : directions) {
                    int nx = curCell[0] + direction[0];
                    int ny = curCell[1] + direction[1];
                    if (nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny] && forest.get(nx).get(ny) != 0) {
                        queue.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            minSteps++;
        }
        return -1;
    }

    private List<int[]> getAllTreeHights(List<List<Integer>> forest) {
        List<int[]> treeHeights = new LinkedList<>();
        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(0).size(); j++) {
                int tmpVal = forest.get(i).get(j);
                if (tmpVal > 1) {
                    int[] element = new int[3];
                    element[0] = i;
                    element[1] = j;
                    element[2] = tmpVal;
                    treeHeights.add(element);
                }
            }
        }
        return treeHeights;
    }
}
