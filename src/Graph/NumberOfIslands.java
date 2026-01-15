package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        int island = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(grid, visited, i, j);
                    island++;
                }
            }
        }
        return island;
    }

    private void dfs(char[][] grid, boolean[][] visited, int i, int j) {
        int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
        visited[i][j] = true;
        for (int[] dir : direction) {
            int row = i + dir[0];
            int col = j + dir[1];
            if (row < grid.length && row >= 0 && col >= 0
                    && col < grid[0].length) {
                if (!visited[row][col] && grid[row][col] == '1') {
                    dfs(grid, visited, row, col);
                }
            }
        }
        return;
    }

    private void bfs(char[][] grid, boolean[][] visited, int row, int col, int[][] directions) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] { row, col });
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            for (int[] dir : directions) {
                int newX = point[0] + dir[0];
                int newY = point[1] + dir[1];

                if (newX >= 0 && newX < grid.length &&
                        newY >= 0 && newY < grid[0].length &&
                        !visited[newX][newY] &&
                        grid[newX][newY] == '1') {

                    visited[newX][newY] = true;
                    queue.offer(new int[] { newX, newY });
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                { '1', '1', '0', '0', '0' },
                { '1', '1', '0', '0', '0' },
                { '0', '0', '1', '0', '0' },
                { '0', '0', '0', '1', '1' }
        };

        System.out.println(new NumberOfIslands().numIslands(grid));
    }
}
