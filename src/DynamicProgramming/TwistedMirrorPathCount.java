package DynamicProgramming;

import java.util.*;

public class TwistedMirrorPathCount {
    private static final int MOD = 1000000007;
    private int m, n;
    private int[][] grid;
    private int[][] dp;

    public int uniquePaths(int[][] grid) {
        this.grid = grid;
        this.m = grid.length;
        this.n = grid[0].length;
        this.dp = new int[m][n];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return dfs(0, 0);
    }

    private int dfs(int i, int j) {
        if (i == m - 1 && j == n - 1) return 1;
        if (i < 0 || i >= m || j < 0 || j >= n) return 0;
        if (dp[i][j] != -1) return dp[i][j];

        long res = 0;

        // Move right
        int[] right = move(i, j, true);
        if (right != null) {
            res += dfs(right[0], right[1]);
        }

        // Move down
        int[] down = move(i, j, false);
        if (down != null) {
            res += dfs(down[0], down[1]);
        }

        return dp[i][j] = (int) (res % MOD);
    }

    // Move: dir=true → right, dir=false → down
    private int[] move(int i, int j, boolean dirRight) {
        int newI = i + (dirRight ? 0 : 1);
        int newJ = j + (dirRight ? 1 : 0);

        if (newI >= m || newJ >= n) return null;

        if (grid[newI][newJ] == 0) {
            return new int[]{newI, newJ};
        } else {
            // Mirror reflects: right → down, down → right
            return move(newI, newJ, !dirRight);
        }
    }

    public static void main(String[] args) {
        TwistedMirrorPathCount solution = new TwistedMirrorPathCount();

        int[][] grid1 = {{0, 1, 0}, {0, 0, 1}, {1, 0, 0}};
        System.out.println(solution.uniquePaths(grid1)); // Expected: 5

        int[][] grid2 = {{0, 1, 1}, {1, 1, 0}};
        System.out.println(solution.uniquePaths(grid2)); // Expected: 1
    }
}
