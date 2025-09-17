package DynamicProgramming;

public class DungeonGame {
    public static final int max = Integer.MAX_VALUE - 5;

    public static int recursion(int[][] dungeon, int[][] dp, int i, int j, int n, int m) {

        if (i >= n || j >= m) {
            return max;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (i == n - 1 && j == m - 1) {
            if (dungeon[i][j] <= 0) {
                return (-1 * dungeon[i][j]) + 1;
            }
            return 1;
        }

        int down = recursion(dungeon, dp, i + 1, j, n, m);
        int right = recursion(dungeon, dp, i, j + 1, n, m);

        int min = Math.min(down, right) + (-1 * dungeon[i][j]);
        dp[i][j] = min <= 0 ? 1 : min;
        return dp[i][j];

    }

    public static int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = -1;
            }
        }

        return recursion(dungeon, dp, 0, 0, n, m);
    }

    public static int tabulation(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;

        int[][] dp = new int[n + 1][m + 1];

        return 1;
    }

    public static void main(String[] args) {
        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};

        System.out.println(calculateMinimumHP(dungeon));
    }
}
