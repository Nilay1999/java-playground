package Backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueen {
    private static List<List<String>> placeQueen(int n) {
        char[][] board = new char[n][n];
        List<List<String>> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        backtrack(0, board, answer);
        return answer;
    }

    private static void backtrack(int row, char[][] board, List<List<String>> result) {
        if (row == board.length) {
            result.add(append(board));
            return;
        }
        for (int c = 0; c < board.length; c++) {
            if (canPlaceQueen(row, c, board)) {
                board[row][c] = 'Q';
                backtrack(row + 1, board, result);
                board[row][c] = '.';
            }
        }
    }

    private static boolean canPlaceQueen(int row, int col, char[][] board) {
        // Check for queen in the same column
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
        return true;
    }

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
