package DynamicProgramming;

public class UniquePathsWithObstacles {
    private int[][] memo;

    private int recursion(int i, int j, int[][] arr) {
        if (i == 0 && j == 0 && arr[i][j] == 0) {
            return 1;
        }
        if (i < 0 || j < 0 || arr[i][j] == 1) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int up = recursion(i - 1, j, arr);
        int left = recursion(i, j - 1, arr);
        memo[i][j] = up + left;
        return memo[i][j];
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
        // TODO: Implement bottom-up dynamic programming version
        return -1;
    }

    public static void main(String[] args) {
        int[][] arr = {{0, 0, 0, 1}, {0, 1, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 0}};

        UniquePathsWithObstacles uniquePath2 = new UniquePathsWithObstacles();
        System.out.println("Memoization: " + uniquePath2.memoization(arr));  // Expected output
    }
}
