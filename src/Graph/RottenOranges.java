package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        int fresh = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] { i, j });
                    visited[i][j] = true;
                }
                if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }
        if (fresh == 0)
            return 0;

        int[][] direction = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
        int mins = 0;
        while (!queue.isEmpty()) {
            int N = queue.size();
            while (N-- > 0) {
                int[] location = queue.poll();
                for (int[] dir : direction) {
                    int x = location[0];
                    int y = location[1];

                    int row = x + dir[0];
                    int col = y + dir[1];

                    if (row >= n || row < 0 || col >= m || col < 0 || grid[row][col] != 1)
                        continue;

                    grid[row][col] = 2;
                    queue.offer(new int[] { row, col });
                    fresh--;
                }
            }
            mins++;
        }

        return fresh == 0 ? (mins - 1) : -1;
    }

    public static void main(String[] args) {
        int[][] oranges = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
        System.out.println(new RottenOranges().orangesRotting(oranges));
    }
}
