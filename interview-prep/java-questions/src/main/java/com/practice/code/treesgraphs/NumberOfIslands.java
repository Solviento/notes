/*Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
*/
package com.practice.code.treesgraphs;

import com.practice.code.runner.CodeRunner;

class NumberOfIslands implements CodeRunner {
    // use DFS like approach to recursively go through adjacent cells and mark off all visited matrix cells as 'X'
    // whenever a '1' is found, recursively mark off all adjacent island cells up, down, left, right
    // Time: O(n*m) Space: O(n*m)
    public int numIslands(char[][] grid) {
        int counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    DFS(grid, i, j);
                    counter++;
                }
            }
        }
        return counter;
    }

    public void DFS(char[][] grid, int x, int y) {
        // base case, terminate recursion once out of bounds or when grid[x][y] == '/'
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != '1') {
            return;
        }
        grid[x][y] = 'X';
        // up one
        DFS(grid, x + 1, y);
        // down one
        DFS(grid, x - 1, y);
        // right one
        DFS(grid, x, y + 1);
        // left one
        DFS(grid, x, y - 1);
    }

    @Override
    public void run() {

    }
}