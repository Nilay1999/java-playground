package DynamicProgramming;

import java.util.Arrays;

public class CoinChange {
    /**
     * Coin Change Algorithm - Find minimum coins to make amount:
     * 
     * BOTTOM-UP TABULATION APPROACH:
     * dp[i] = minimum coins needed to make amount i
     * For each coin, update all amounts that can be formed
     * dp[i] = min(dp[i], dp[i-coin] + 1)
     * 
     * TOP-DOWN MEMOIZATION APPROACH:
     * For each coin, decide to take it (unlimited times) or skip it
     * memo[amount][index] = minimum coins for amount starting from index
     * 
     * Time: O(amount × coins), Space: O(amount)
     */

    // Bottom-Up Tabulation
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    // Top-Down Memoization
    public static int coinChangeMemoization(int[] coins, int amount) {
        int n = coins.length;
        Integer[][] memo = new Integer[amount + 1][n + 1];

        int res = recursion(coins, 0, amount, memo);

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private static int recursion(int[] coins, int idx, int value, Integer[][] memo) {
        int n = coins.length;
        if (value == 0) return 0;
        if (idx >= n || value < 0) return Integer.MAX_VALUE;

        if (memo[value][idx] != null) return memo[value][idx];

        int takeIt = recursion(coins, idx, value - coins[idx], memo);
        if (takeIt != Integer.MAX_VALUE) {
            takeIt += 1;
        }

        int leaveIt = recursion(coins, idx + 1, value, memo);

        memo[value][idx] = Math.min(takeIt, leaveIt);
        return memo[value][idx];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;

        System.out.println("Tabulation: " + coinChange(coins, amount));         // Output: 3
        System.out.println("Memoization: " + coinChangeMemoization(coins, amount)); // Output: 3
    }
}

