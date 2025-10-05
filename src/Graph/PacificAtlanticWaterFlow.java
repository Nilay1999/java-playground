package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticWaterFlow {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        int n = heights.length;
        int m = heights[0].length;
        boolean[][] pac = new boolean[n][m];
        boolean[][] alt = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            dfs(pac, heights, i, 0, heights[i][0]);
            dfs(alt, heights, i, m - 1, heights[i][m - 1]);
        }
        for (int j = 0; j < m; j++) {
            dfs(pac, heights, 0, j, heights[0][j]);
            dfs(alt, heights, n - 1, j, heights[n - 1][j]);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (pac[i][j] && alt[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
        return result;
    }

    private void dfs(boolean[][] visited, int[][] heights, int i, int j, int prev) {
        int n = heights.length;
        int m = heights[0].length;

        if (i >= n || i < 0 || j >= m || j < 0 || visited[i][j] || prev > heights[i][j]) {
            return;
        }

        int[][] direction = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
        visited[i][j] = true;
        for (int[] dir : direction) {
            int row = dir[0] + i;
            int col = dir[1] + j;
            dfs(visited, heights, row, col, heights[i][j]);
        }
        return;
    }

    public static void main(String[] args) {
        int[][] water = {
                { 1, 2, 2, 3, 5 },
                { 3, 2, 3, 4, 4 },
                { 2, 4, 5, 3, 1 },
                { 6, 7, 1, 4, 5 },
                { 5, 1, 1, 2, 4 }
        };
        System.out.println(new PacificAtlanticWaterFlow().pacificAtlantic(water));
    }
}
