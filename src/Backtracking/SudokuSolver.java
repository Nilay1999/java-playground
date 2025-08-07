package Backtracking;

public class SudokuSolver {
    private static final int SIZE = 9;

    public boolean solveSudoku(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) { // Find an empty cell
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num; // Tentatively place num
                            if (solveSudoku(board)) {
                                return true; // Solved!
                            }
                            board[row][col] = 0; // Backtrack
                        }
                    }
                    return false; // No valid number found, trigger backtracking
                }
            }
        }
        return true; // No empty cells left, board solved
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        int boxRowStart = row - row % 3;
        int boxColStart = col - col % 3;

        for (int i = 0; i < SIZE; i++) {
            // Check row
            if (board[row][i] == num) {
                return false;
            }
            // Check column
            if (board[i][col] == num) {
                return false;
            }
            // Check 3x3 box
            int boxRow = boxRowStart + i / 3;
            int boxCol = boxColStart + i % 3;
            if (board[boxRow][boxCol] == num) {
                return false;
            }
        }
        return true;
    }


    // Utility method to print the board
    public void printBoard(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {{5, 3, 0, 0, 7, 0, 0, 0, 0}, {6, 0, 0, 1, 9, 5, 0, 0, 0}, {0, 9, 8, 0, 0, 0, 0, 6, 0}, {8, 0
                , 0, 0, 6, 0, 0, 0, 3}, {4, 0, 0, 8, 0, 3, 0, 0, 1}, {7, 0, 0, 0, 2, 0, 0, 0, 6}, {0, 6, 0, 0, 0, 0,
                2, 8, 0}, {0, 0, 0, 4, 1, 9, 0, 0, 5}, {0, 0, 0, 0, 8, 0, 0, 7, 9}};

        SudokuSolver solver = new SudokuSolver();
        if (solver.solveSudoku(board)) {
            System.out.println("Sudoku solved successfully:");
            solver.printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }
}
