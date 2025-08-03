package DynamicProgramming;

import java.util.Arrays;

public class CoinChangeII {

    // ------------------------
    // Top-Down: Memoization
    // ------------------------
    public static int coinChangeMemoization(int[] coins, int amount) {
        int n = coins.length;
        int[][] memo = new int[n + 1][amount + 1];

        // Fill memo with -1
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return recursion(0, 0, coins, amount, memo);
    }

    private static int recursion(int idx, int value, int[] coins, int amount, int[][] memo) {
        if (value > amount) return 0;
        if (idx >= coins.length) return 0;
        if (value == amount) return 1;
        if (memo[idx][value] != -1) return memo[idx][value];

        int take = recursion(idx, value + coins[idx], coins, amount, memo);
        int skip = recursion(idx + 1, value, coins, amount, memo);

        memo[idx][value] = take + skip;
        return memo[idx][value];
    }

    // ------------------------
    // Bottom-Up: Tabulation
    // ------------------------
    public static int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n + 1][amount + 1];

        // Base case: only 1 way to make amount 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                int take = 0;
                if (coins[i - 1] <= j) {
                    take = dp[i][j - coins[i - 1]];
                }
                int skip = dp[i - 1][j];
                dp[i][j] = take + skip;
            }
        }

        return dp[n][amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 4, 3, 5};
        int amount = 5;

        System.out.println("Memoization: " + coinChangeMemoization(coins, amount)); // ➜ 16
        System.out.println("Tabulation: " + coinChange(coins, amount));             // ➜ 16
    }
}

