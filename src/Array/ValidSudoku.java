package Array;

import java.util.HashSet;
import java.util.Set;

public class ValidSudoku {
    /**
     * Sudoku Validation Algorithm:
     * Use HashSet to track seen numbers with unique identifiers for:
     * - Row constraint: "number in row:i"
     * - Column constraint: "number in col:j" 
     * - 3x3 box constraint: "number in sq:box_row:box_col"
     * 
     * For each non-empty cell, check if any constraint is violated.
     * Box index calculated as (i/3, j/3) for 9x9 grid.
     * 
     * Time: O(1) - fixed 9x9 grid, Space: O(1) - at most 243 entries in set
     */

    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();
        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                String rowCheck = board[i][j] + "in row:" + i;
                String colCheck = board[i][j] + "in col:" + j;
                String sqCheck = board[i][j] + "in sq:" + (i / 3) + ":" + (j / 3);
                if (board[i][j] != '.') {
                    if (set.contains(rowCheck) || set.contains(colCheck) || set.contains(sqCheck)) {
                        return false;
                    } else {
                        set.add(rowCheck);
                        set.add(colCheck);
                        set.add(sqCheck);
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] sudoko = { { '8', '3', '.', '.', '7', '.', '.', '.', '.' }, { '6', '.', '.', '1', '9', '5', '.', '.',
                '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' }, { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' }, { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.',
                        '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.',
                        '.', '.', '8', '.', '.', '7', '9' } };

        System.out.println(new ValidSudoku().isValidSudoku(sudoko));
    }
}
