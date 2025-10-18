package Graph;

import java.util.Arrays;

public class SurroundedRegion {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O' && !visited[i][0])
                dfs(board, visited, i, 0);

            if (board[i][n - 1] == 'O' && !visited[i][n - 1])
                dfs(board, visited, i, n - 1);
        }

        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O' && !visited[0][j])
                dfs(board, visited, 0, j);

            if (board[m - 1][j] == 'O' && !visited[m - 1][j])
                dfs(board, visited, m - 1, j);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (board[i][j] == 'O' && !visited[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    private void dfs(char[][] board, boolean[][] visited, int i, int j) {
        int[][] direction = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
        visited[i][j] = true;
        for (int[] dir : direction) {
            int row = dir[0] + i;
            int col = dir[1] + j;

            if (row < board.length && row >= 0 && col < board[0].length && col >= 0 && board[row][col] == 'O'
                    && !visited[row][col]) {
                dfs(board, visited, row, col);
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {
                { 'X', 'X', 'X', 'X' },
                { 'X', 'O', 'O', 'X' },
                { 'X', 'X', 'O', 'X' },
                { 'X', 'O', 'X', 'X' }
        };
        new SurroundedRegion().solve(board);
        System.out.println(Arrays.deepToString(board));
    }
}
