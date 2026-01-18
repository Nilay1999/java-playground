package Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * N-Queens Problem Algorithm (Backtracking):
 * Place N queens on an N×N chessboard such that no two queens attack each other.
 * 
 * PROBLEM CONSTRAINTS:
 * - No two queens in same row
 * - No two queens in same column
 * - No two queens on same diagonal
 * 
 * BACKTRACKING STRATEGY:
 * 1. Place queens row by row (one queen per row)
 * 2. For each row, try placing queen in each column
 * 3. Check if placement is safe (no conflicts)
 * 4. If safe, place queen and recurse to next row
 * 5. If all rows filled, found a solution
 * 6. Backtrack: remove queen and try next column
 * 
 * SAFETY CHECK:
 * - Column: check all rows above
 * - Upper-left diagonal: check (row-1, col-1), (row-2, col-2), ...
 * - Upper-right diagonal: check (row-1, col+1), (row-2, col+2), ...
 * 
 * Example: n=4 → 2 solutions
 * Solution 1:    Solution 2:
 * . Q . .        . . Q .
 * . . . Q        Q . . .
 * Q . . .        . . . Q
 * . . Q .        . Q . .
 * 
 * Time: O(N!), Space: O(N²) for board
 */
public class NQueen {
    private static List<List<String>> placeQueen(int n) {
        // Initialize empty board with '.'
        char[][] board = new char[n][n];
        List<List<String>> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        // Start backtracking from row 0
        backtrack(0, board, answer);
        return answer;
    }

    private static void backtrack(int row, char[][] board, List<List<String>> result) {
        // Base case: all queens placed successfully
        if (row == board.length) {
            result.add(append(board)); // add board configuration to results
            return;
        }
        
        // Try placing queen in each column of current row
        for (int c = 0; c < board.length; c++) {
            // Check if placing queen at (row, c) is safe
            if (canPlaceQueen(row, c, board)) {
                // Place queen
                board[row][c] = 'Q';
                // Recurse to next row
                backtrack(row + 1, board, result);
                // Backtrack: remove queen
                board[row][c] = '.';
            }
        }
    }

    private static boolean canPlaceQueen(int row, int col, char[][] board) {
        // Check for queen in the same column (all rows above)
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
        
        // Check upper-left diagonal
        int i = row - 1, j = col - 1;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 'Q') {
                return false;
            }
            i--;
            j--;
        }
        
        // Check upper-right diagonal
        i = row - 1;
        j = col + 1;
        while (i >= 0 && j < board.length) {
            if (board[i][j] == 'Q') {
                return false;
            }
            i--;
            j++;
        }
        
        // No conflicts found, safe to place queen
        return true;
    }

    // Helper: Convert board to list of strings
    static List<String> append(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 4;
        List<List<String>> ans = placeQueen(n);
        for (List<String> an : ans) {
            for (String s : an) {
                System.out.println(s);
            }
        }
    }
}
