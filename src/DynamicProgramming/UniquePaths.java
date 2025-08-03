package DynamicProgramming;

public class UniquePaths {
    private int[][] memo;

    // Memoization approach
    private int recursion(int n, int m) {
        if (n < 0 || m < 0) {
            return 0;
        }
        if (n == 0 && m == 0) {
            return 1;
        }
        if (memo[n][m] != -1) {
            return memo[n][m];
        }
        int left = recursion(n, m - 1);
        int up = recursion(n - 1, m);
        memo[n][m] = left + up;
        return memo[n][m];
    }

    public int memoization(int n, int m) {
        memo = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = -1;
            }
        }
        return recursion(n - 1, m - 1);
    }

    // Tabulation (bottom-up DP)
    public int tabulation(int n, int m) {
        int[][] dp = new int[n][m];

        // Initialize first row and column with 1s
        for (int i = 0; i < n; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < m; j++) {
            dp[0][j] = 1;
        }

        // Fill rest of the grid
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        UniquePaths paths = new UniquePaths();
        System.out.println("Tabulation: " + paths.tabulation(3, 7));       // Output: 28
        System.out.println("Memoization: " + paths.memoization(3, 7));     // Output: 28
    }
}

