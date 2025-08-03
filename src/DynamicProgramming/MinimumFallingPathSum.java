package DynamicProgramming;

import java.util.Arrays;

public class MinimumFallingPathSum {
    private int[][] memo;

    // Recursion + Memoization
    private int recursion(int i, int j, int[][] matrix) {
        int m = matrix[0].length;

        if (j < 0 || j >= m) return Integer.MAX_VALUE;
        if (i == 0) return matrix[0][j];
        if (memo[i][j] != -1) return memo[i][j];

        int left = matrix[i][j] + recursion(i - 1, j - 1, matrix);
        int up = matrix[i][j] + recursion(i - 1, j, matrix);
        int right = matrix[i][j] + recursion(i - 1, j + 1, matrix);

        memo[i][j] = Math.min(left, Math.min(up, right));
        return memo[i][j];
    }

    public int memoization(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        memo = new int[n][m];
        for (int[] row : memo) Arrays.fill(row, -1);

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < m; j++) {
            min = Math.min(min, recursion(n - 1, j, matrix));
        }

        return min;
    }

    // Tabulation (Bottom-up)
    public int tabulation(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];

        System.arraycopy(matrix[0], 0, dp[0], 0, m);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int above = dp[i - 1][j];
                int left = j > 0 ? dp[i - 1][j - 1] : Integer.MAX_VALUE;
                int right = j < m - 1 ? dp[i - 1][j + 1] : Integer.MAX_VALUE;

                dp[i][j] = matrix[i][j] + Math.min(above, Math.min(left, right));
            }
        }

        int min = Integer.MAX_VALUE;
        for (int val : dp[n - 1]) {
            min = Math.min(min, val);
        }

        return min;
    }

    public static void main(String[] args) {
        MinimumFallingPathSum solver = new MinimumFallingPathSum();
        int[][] matrix = {{-19, 57}, {-40, -5}};

        System.out.println("Memoization: " + solver.memoization(matrix));  // Output: -59
        System.out.println("Tabulation: " + solver.tabulation(matrix));    // Output: -59
    }
}

