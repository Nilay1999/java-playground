package backtracking;

public class SudokuSolver {
    private static boolean isValid(char[][] board, int row, int col, char num) {
        for (int i = 0; i<board[0].length; i++) {
            if (i != row && board[0][i] == num) {
                return false;
            }
        }

        for (int j = 0; j<board[0].length; j++) {
            if (j != col && board[j][0] == num) {
                return false;
            }
        }

        int sqRow = (int) Math.floor((double) row /3);
        int sqCol = (int) Math.floor((double) col /3);


        for (int i=sqRow; i<sqRow + 3; i++) {
            for (int j=sqCol; j<sqCol+3; j++) {

            }
        }
        return true;
    }

    public void solveSudoku(char[][] board) {

    }
}

// 00 01 02 03 04 05 06 07 08 09
// 10 11 12 13 14 15 16 17 18 19
// 20 21 22 23 24 25 26 27 28 29
// 30 31 32 33 34 35 36 37 38 39
// 40 41 42 43 44 45 46 47 48 49
// 50 51 52 53 54 55 56 57 58 59
// 60 61 62 63 64 65 66 67 68 69
// 70 71 72 73 74 75 76 77 78 79
// 80 81 82 83 84 85 86 87 88 89