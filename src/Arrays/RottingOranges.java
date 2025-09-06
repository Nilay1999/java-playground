package Arrays;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    private static class Pair {
        int row;
        int col;
        int tm;

        Pair(int row, int col, int tm) {
            this.row = row;
            this.col = col;
            this.tm = tm;
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
    }

    public void orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> queue = new LinkedList<>();

        int[][] visited = new int[n][m];
        int totalFresh = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    totalFresh++;
                }
                if (grid[i][j] == 2) {
                    queue.add(new Pair(i, j, 0));
                    visited[i][j] = 2;
                } else {
                    visited[i][j] = 0;
                }
            }
        }

        int[] rowDir = {-1, 0, 1, 0};
        int[] colDir = {0, 1, 0, -1};

        int total = 0;

        while (!queue.isEmpty()) {
            int r = queue.peek().row;
            int c = queue.peek().col;
            int tm = queue.peek().tm;

        }
    }
}
