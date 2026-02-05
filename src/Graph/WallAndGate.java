package Graph;

import java.util.LinkedList;
import java.util.Queue;

public class WallAndGate {
    static int INF = Integer.MAX_VALUE;

    public int[][] findDistance(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[row][col];
        int[][] direction = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    visited[i][j] = true;
                    queue.offer(new int[] { i, j });
                }
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                int x = point[0];
                int y = point[1];
                for (int[] dir : direction) {
                    int newRow = x + dir[0];
                    int newCol = y + dir[1];

                    if (newRow >= row || newRow < 0 || newCol >= col || newCol < 0 || visited[newRow][newCol]
                            || grid[newRow][newCol] == -1) {
                        continue;
                    }

                    if (grid[newRow][newCol] == INF) {
                        grid[newRow][newCol] = grid[x][y] + 1;
                        visited[newRow][newCol] = true;
                        queue.offer(new int[] { newRow, newCol });
                    }
                }
            }
        }

        return grid;
    }

    public static void main(String[] args) {

        int[][] grid = {
                { INF, -1, 0, INF },
                { INF, INF, INF, -1 },
                { INF, -1, INF, -1 },
                { 0, -1, INF, INF }
        };
        int[][] ans = new WallAndGate().findDistance(grid);

        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
