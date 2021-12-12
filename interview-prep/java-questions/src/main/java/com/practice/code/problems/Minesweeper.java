package com.practice.code.problems;

public class Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        int boardLength = board.length, boardWidth = board[0].length;
        int clickPositionY = click[0], clickPositionX = click[1];

        if (board[clickPositionY][clickPositionX] == 'M') { // Mine
            board[clickPositionY][clickPositionX] = 'X';
        }
        else { // Empty
            // Get number of mines first.
            int mineCount = 0;
            for (int yIter = -1; yIter < 2; yIter++) {
                for (int xIter = -1; xIter < 2; xIter++) {
                    if (yIter == 0 && xIter == 0) continue;
                    int r = clickPositionY + yIter, c = clickPositionX + xIter;
                    if (r < 0 || r >= boardLength ||  c < 0 || c >= boardWidth) continue;
                    if (board[r][c] == 'M' || board[r][c] == 'X') mineCount++;
                }
            }

            if (mineCount > 0) { // If it is not a 'B', stop further DFS.
                board[clickPositionY][clickPositionX] = (char)(mineCount + '0');
            }
            else { // Continue DFS to adjacent cells.
                board[clickPositionY][clickPositionX] = 'B';
                for (int i = -1; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        if (i == 0 && j == 0) continue;
                        int r = clickPositionY + i, c = clickPositionX + j;
                        if (r < 0 || r >= boardLength || c < 0 || c < 0 || c >= boardWidth) continue;
                        if (board[r][c] == 'E') updateBoard(board, new int[] {r, c});
                    }
                }
            }
        }

        return board;
    }
}
