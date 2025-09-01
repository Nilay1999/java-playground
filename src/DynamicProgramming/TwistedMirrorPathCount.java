package DynamicProgramming;

import java.util.*;

public class TwistedMirrorPathCount {
    int m, n;
    int[][] g;
    Long[][][] cache;
    int MOD = 1000000007;

    long dfs(int x, int y, int from) {
        if (x < 0 || y < 0 || x >= m || y >= n) return 0;
        if (x == m - 1 && y == n - 1) return 1;

        if (cache[x][y][from] != null) return cache[x][y][from];

        long result = 0;

        if (g[x][y] == 0) {
            result = (dfs(x + 1, y, 1) + dfs(x, y + 1, 0)) % MOD;
        } else {
            if (from == 0) {
                result = dfs(x + 1, y, 1);
            } else {
                result = dfs(x, y + 1, 0);
            }
        }

        return cache[x][y][from] = result;
    }

    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        g = grid;
        cache = new Long[m][n][2];

        return (int) dfs(0, 0, 0);
    }

    public static void main(String[] args) {
        TwistedMirrorPathCount solution = new TwistedMirrorPathCount();

        int[][] grid1 = {{0, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        System.out.println(solution.uniquePaths(grid1)); // Expected: 5

        int[][] grid2 = {{0, 1, 1}, {1, 1, 0}};
        System.out.println(solution.uniquePaths(grid2)); // Expected: 1
    }
}
