package Graph;

import java.util.LinkedList;
import java.util.Queue;

class ZeroOneMatrix {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        boolean[][] visited = new boolean[m][n];
        int[][] direction = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    visited[i][j] = true;
                    queue.offer(new int[] { i, j });
                }
            }
        }
        if (queue.isEmpty() || queue.size() == n * m)
            return mat;

        while (!queue.isEmpty()) {
            int[] item = queue.poll();
            int x = item[0];
            int y = item[1];

            for (int[] dir : direction) {
                int row = x + dir[0];
                int col = y + dir[1];

                if (row < m && row >= 0 && col < n && col >= 0 && !visited[row][col]) {
                    mat[row][col] = mat[x][y] + 1;
                    visited[row][col] = true;
                    queue.offer(new int[] { row, col });
                }
            }
        }
        return mat;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 0, 0, 0 }, { 0, 1, 0 }, { 1, 1, 1 } };
        System.out.println(new ZeroOneMatrix().updateMatrix(matrix));
    }
}