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
    static int INF = (int) 1e9;

    // Bottom-Up Tabulation: Build solution from smallest subproblems
    public static int coinChange(int[] coins, int amount) {
        // dp[i] = minimum coins needed to make amount i
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, INF); // initialize with "impossible"
        dp[0] = 0; // base case: 0 coins needed for amount 0

        // For each coin denomination
        for (int coin : coins) {
            // Update all amounts that can be formed using this coin
            for (int i = coin; i <= amount; i++) {
                // If amount (i-coin) is achievable, we can make amount i
                if (dp[i - coin] != INF) {
                    // Take minimum: current way vs using this coin
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // Return result: -1 if impossible, else minimum coins
        return dp[amount] == INF ? -1 : dp[amount];
    }

    // Top-Down Memoization: Solve recursively with caching
    public static int coinChangeMemoization(int[] coins, int amount) {
        int n = coins.length;
        // memo[amount][index] = min coins for amount starting from index
        Integer[][] memo = new Integer[amount + 1][n + 1];

        int res = recursion(coins, 0, amount, memo);

        return res == INF ? -1 : res;
    }

    private static int recursion(int[] coins, int idx, int value, Integer[][] memo) {
        int n = coins.length;
        // Base case: exact amount reached
        if (value == 0)
            return 0;
        // Base case: out of coins or negative amount
        if (idx >= n || value < 0)
            return INF;

        // Return cached result if available
        if (memo[value][idx] != null)
            return memo[value][idx];

        // Option 1: Take current coin (can reuse same coin)
        int takeIt = recursion(coins, idx, value - coins[idx], memo);
        if (takeIt != INF) {
            takeIt += 1; // add 1 coin to count
        }

        // Option 2: Skip current coin, move to next
        int leaveIt = recursion(coins, idx + 1, value, memo);

        // Store and return minimum of both options
        memo[value][idx] = Math.min(takeIt, leaveIt);
        return memo[value][idx];
    }

    public static void main(String[] args) {
        int[] coins = { 1, 2, 5 };
        int amount = 11;

        System.out.println("Tabulation: " + coinChange(coins, amount)); // Output: 3
        System.out.println("Memoization: " + coinChangeMemoization(coins, amount)); // Output: 3
    }
}
