/*Let's play the minesweeper game (Wikipedia, online game)!

You are given an m x n char matrix board representing the game board where:

'M' represents an unrevealed mine,
'E' represents an unrevealed empty square,
'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
'X' represents a revealed mine.
You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').

Return the board after revealing this position according to the following rules:

If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
Return the board when no more squares will be revealed.

Example 1:
Input: board = [["E","E","E","E","E"],["E","E","M","E","E"],["E","E","E","E","E"],["E","E","E","E","E"]], click = [3,0]
Output: [["B","1","E","1","B"],["B","1","M","1","B"],["B","1","1","1","B"],["B","B","B","B","B"]]
*/
package com.practice.code.arrays.second;

import com.practice.code.runner.CodeRunner;

public class Minesweeper implements CodeRunner {
    public char[][] updateBoard(char[][] board, int[] click) {
        int boardLength = board.length, boardWidth = board[0].length;
        int clickPositionY = click[0], clickPositionX = click[1];

        if (board[clickPositionY][clickPositionX] == 'M') { // Mine
            board[clickPositionY][clickPositionX] = 'X';
        } else { // Empty
            // Get number of mines first.
            int mineCount = 0;
            for (int yIter = -1; yIter < 2; yIter++) {
                for (int xIter = -1; xIter < 2; xIter++) {
                    if (yIter == 0 && xIter == 0) continue;
                    int r = clickPositionY + yIter, c = clickPositionX + xIter;
                    if (r < 0 || r >= boardLength || c < 0 || c >= boardWidth) continue;
                    if (board[r][c] == 'M' || board[r][c] == 'X') mineCount++;
                }
            }

            if (mineCount > 0) { // If it is not a 'B', stop further DFS.
                board[clickPositionY][clickPositionX] = (char) (mineCount + '0');
            } else { // Continue DFS to adjacent cells.
                board[clickPositionY][clickPositionX] = 'B';
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;
                        int r = clickPositionY + i, c = clickPositionX + j;
                        if (r < 0 || r >= boardLength || c < 0 || c < 0 || c >= boardWidth) continue;
                        if (board[r][c] == 'E') updateBoard(board, new int[]{r, c});
                    }
                }
            }
        }

        return board;
    }

    @Override
    public void run() {

    }
}
