package Graph;

import java.util.Arrays;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int start = image[sr][sc];
        dfs(image, sr, sc, color, start);
        return image;
    }

    private void dfs(int[][] board, int i, int j, int color, int start) {
        int[][] direction = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        if (board[i][j] == start)
            board[i][j] = color;
        for (int[] dir : direction) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (row >= board.length || row < 0 || col >= board[0].length || col < 0 || board[row][col] == color
                    || board[row][col] != start) {
                continue;
            }
            dfs(board, row, col, color, start);
        }
        return;
    }

    public static void main(String[] args) {
        int[][] image = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
        int sr = 1;
        int sc = 1;
        int color = 2;

        int[][] ans = new FloodFill().floodFill(image, sr, sc, color);
        System.out.println(Arrays.deepToString(ans));
    }
}
