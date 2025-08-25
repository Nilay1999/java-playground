package DynamicProgramming;

public class CountSquareSubmatricesWithAllOnes {
    public static int countSquares(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] dp = new int[n][m];
        int ans = 0;

        // Copy first row and column, and count directly
        for (int i = 0; i < n; i++) {
            dp[i][0] = matrix[i][0];
            ans += dp[i][0];
        }
        for (int j = 1; j < m; j++) { // start from 1 to avoid double counting (0,0)
            dp[0][j] = matrix[0][j];
            ans += dp[0][j];
        }

        // Fill the dp table
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                } else {
                    dp[i][j] = 0;
                }
                ans += dp[i][j]; // directly add the count
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {{0, 1, 1, 1}, {1, 1, 1, 1}, {0, 1, 1, 1}};

        System.out.println(countSquares(matrix));
    }
}
