package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfEnclaves {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && (i == 0 || i == m - 1 || j == 0 || j == n - 1)) {
                    queue.offer(new int[] { i, j });
                    visited[i][j] = true;
                }
            }
        }

        int[][] direction = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

        while (!queue.isEmpty()) {
            int[] box = queue.poll();
            int x = box[0];
            int y = box[1];

            for (int[] dir : direction) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] == 1 && !visited[nx][ny]) {
                    queue.offer(new int[] { nx, ny });
                    visited[nx][ny] = true;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = { { 0, 0, 0, 0 }, { 1, 0, 1, 0 }, { 0, 1, 1, 0 }, { 0, 0, 0, 0 } };
        System.out.println(new NumberOfEnclaves().numEnclaves(grid));
    }
}
