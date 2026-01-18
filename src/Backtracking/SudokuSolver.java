package Backtracking;

/**
 * Sudoku Solver Algorithm (Backtracking):
 * Fill a 9x9 Sudoku board following standard Sudoku rules.
 * 
 * SUDOKU RULES:
 * 1. Each row must contain digits 1-9 without repetition
 * 2. Each column must contain digits 1-9 without repetition
 * 3. Each 3x3 box must contain digits 1-9 without repetition
 * 
 * BACKTRACKING STRATEGY:
 * 1. Find next empty cell (marked with '.')
 * 2. Try placing digits 1-9 in that cell
 * 3. Check if placement is valid (row, column, box constraints)
 * 4. If valid, place digit and recurse to fill next cell
 * 5. If recursion succeeds, solution found
 * 6. If recursion fails, backtrack: remove digit and try next
 * 7. Base case: no empty cells left → board is solved
 * 
 * VALIDATION:
 * - Check row: no duplicate in same row
 * - Check column: no duplicate in same column
 * - Check 3x3 box: no duplicate in same box
 * 
 * Time: O(9^(empty_cells)) - worst case, Space: O(1) excluding recursion
 */
public class SudokuSolver {
    private static final int SIZE = 9;

    public void solveSudoku(char[][] board) {
        fill(board);
    }

    private boolean fill(char[][] board) {
        char[] nums = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
        
        // Find next empty cell
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                // If cell is empty
                if (board[i][j] == '.') {
                    // Try each digit 1-9
                    for (char num : nums) {
                        // Check if placing num is valid
                        if (isValid(board, i, j, num)) {
                            // Place digit
                            board[i][j] = num;
                            // Recursively fill rest of board
                            if (fill(board)) {
                                return true; // solution found
                            } else {
                                // Backtrack: remove digit
                                board[i][j] = '.';
                            }
                        }
                    }
                    // No valid digit found for this cell
                    return false;
                }
            }
        }
        // No empty cells left, board is solved
        return true;
    }

    // Check if placing num at (row, col) is valid
    private boolean isValid(char[][] board, int row, int col, char num) {
        // Check row and column
        for (int i = 0; i < SIZE; i++) {
            // Check if num already in row
            if (board[row][i] == num) {
                return false;
            }
            // Check if num already in column
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check 3x3 box
        // Find top-left corner of box
        int boxX = row / 3 * 3;
        int boxY = col / 3 * 3;

        // Check all cells in 3x3 box
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + boxX][j + boxY] == num) {
                    return false;
                }
            }
        }
        
        // No conflicts found
        return true;
    }

    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.',
                '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.',
                '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.',
                '.', '.', '8', '.', '.', '7', '9'}};
        new SudokuSolver().solveSudoku(board);
    }
}
