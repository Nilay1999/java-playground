package DynamicProgramming;

import java.util.Arrays;

public class CollectFruitsFromDungeon {
    static int n;
    static int[][] dp;

    public static int recursionForKid3(int row, int col, int[][] fruits) {
        int n = fruits.length;
        // Return 0 if Kid is outside Dungeon or end at point
        if (row < 0 || col < 0 || row >= n || col >= n) return 0;
        if (row <= col) return 0; // Kid 3 must stay below the diagonal
        if (row == n - 1 && col == n - 1) return 0;

        int topRight = recursionForKid3(row - 1, col + 1, fruits);
        int right = recursionForKid3(row, col + 1, fruits);
        int bottomRight = recursionForKid3(row + 1, col + 1, fruits);

        return dp[row][col] = fruits[row][col] + Math.max(topRight, Math.max(bottomRight, right));
    }

    public static int recursionForKid2(int row, int col, int[][] fruits) {
        int n = fruits.length;
        // Return 0 if Kid is outside Dungeon or end at point
        if (row < 0 || col < 0 || row >= n || col >= n) return 0;
        if (row >= col) return 0; // Kid 2 must stay above the diagonal
        if (row == n - 1 && col == n - 1) return 0;
        if (dp[row][col] != -1) return dp[row][col];

        int bottomLeft = recursionForKid2(row + 1, col - 1, fruits);
        int bottom = recursionForKid2(row + 1, col, fruits);
        int bottomRight = recursionForKid2(row + 1, col + 1, fruits);

        return dp[row][col] = fruits[row][col] + Math.max(bottomLeft, Math.max(bottomRight, bottom));
    }

    public static int maxCollectedFruits(int[][] fruits) {
        n = fruits.length;
        dp = new int[n + 1][n + 1];

        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int k1 = 0, k2, k3;
        int n = fruits.length;
        for (int i = 0; i < n; i++) {
            k1 += fruits[i][i];
        }
        k2 = recursionForKid2(0, n - 1, fruits);
        k3 = recursionForKid3(n - 1, 0, fruits);
        return k1 + k2 + k3;
    }

    public static void main(String[] args) {
        int[][] rooms = {{1, 2, 3, 4}, {5, 6, 8, 7}, {12, 11, 10, 9}, {13, 14, 15, 16}};
        System.out.println(maxCollectedFruits(rooms));
    }
}
