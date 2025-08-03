package DynamicProgramming;

public class MinimumSumPath {
    private int[][] memo;

    public MinimumSumPath() {
    }

    private int recursion(int i, int j, int[][] arr) {
        if (i == 0 && j == 0) {
            return arr[i][j];
        }
        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int total = arr[i][j] + Math.min(recursion(i - 1, j, arr), recursion(i, j - 1, arr));
        memo[i][j] = total;
        return total;
    }

    public int memoization(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        memo = new int[n][m];

        // Initialize memo with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                memo[i][j] = -1;
            }
        }

        return recursion(n - 1, m - 1, map);
    }

    public int tabulation(int[][] map) {
        int n = map.length;
        int m = map[0].length;
        int[][] dp = new int[n][m];

        dp[0][0] = map[0][0];

        // First column
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + map[i][0];
        }

        // First row
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + map[0][j];
        }

        // Rest of the grid
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + map[i][j];
            }
        }

        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        int[][] pathArr = {{1, 2, 3}, {4, 5, 6}};

        MinimumSumPath minimumPathSum = new MinimumSumPath();
        System.out.println("Tabulation: " + minimumPathSum.tabulation(pathArr));    // Output: 12
        System.out.println("Memoization: " + minimumPathSum.memoization(pathArr));  // Output: 12
    }
}

