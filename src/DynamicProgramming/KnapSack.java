package DynamicProgramming;

public class KnapSack {
    /**
     * 0/1 Knapsack Algorithm (Memoization):
     * Find maximum profit with weight constraint, each item used at most once.
     * 
     * State: memo[index][capacity] = max profit using items 0..index with given
     * capacity
     * 
     * Recurrence:
     * - If weight[i] > capacity: skip item → memo[i-1][capacity]
     * - Else: max(take item + memo[i-1][capacity-weight[i]], skip item)
     * 
     * Base case: index=0 or capacity=0 → profit=0
     * 
     * Time: O(n × capacity), Space: O(n × capacity)
     */

    private int dfs(Integer[][] memo, int[] profit, int[] weight, int capacity, int idx) {
        // Base case: no capacity left or no items left
        if (capacity == 0 || idx == 0) {
            return 0;
        }

        // Return cached result if available
        if (memo[idx][capacity] != null) {
            return memo[idx][capacity];
        }

        // If current item's weight exceeds capacity, skip it
        if (capacity < weight[idx]) {
            memo[idx][capacity] = dfs(memo, profit, weight, capacity, idx - 1);
            return memo[idx][capacity];
        } else {
            // Option 1: Take current item
            int take = profit[idx] + dfs(memo, profit, weight, capacity - weight[idx], idx - 1);
            // Option 2: Skip current item
            int skip = dfs(memo, profit, weight, capacity, idx - 1);
            // Store and return maximum profit
            memo[idx][capacity] = Math.max(take, skip);
            return memo[idx][capacity];
        }
    }

    private int memoization(int[] profit, int[] weight, int capacity) {
        int n = profit.length;
        // memo[index][capacity] = max profit using items 0..index with given capacity
        Integer[][] memo = new Integer[n + 1][capacity + 1];

        return dfs(memo, profit, weight, capacity, n - 1);
    }

    /**
     * 0/1 Knapsack Algorithm (Tabulation):
     * Find maximum profit with weight constraint, each item used at most once.
     * 
     * State: dp[i][j] = max profit using first i items with capacity j
     * 
     * Recurrence:
     * - If weight[i-1] <= j: dp[i][j] = max(dp[i-1][j], profit[i-1] +
     * dp[i-1][j-weight[i-1]])
     * - Else: dp[i][j] = dp[i-1][j]
     * 
     * Base case: dp[0][j] = 0, dp[i][0] = 0
     * 
     * Time: O(n × capacity), Space: O(n × capacity)
     */

    private int tabulation(int[] profit, int[] weight, int capacity) {
        int n = profit.length;
        // dp[i][j] = max profit using first i items with capacity j
        int[][] dp = new int[n + 1][capacity + 1];

        // Initialize base cases: no items or no capacity means profit 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0; // No capacity
        }
        for (int j = 0; j <= capacity; j++) {
            dp[0][j] = 0; // No items
        }

        // Fill the dp table bottom-up
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weight[i - 1] <= j) {
                    int take = profit[i - 1] + dp[i - 1][j - weight[i - 1]];
                    int skip = dp[i - 1][j];
                    dp[i][j] = Math.max(take, skip);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // The answer is the max profit with all items and full capacity
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int[] weight = { 4, 5, 1 };
        int[] profit = { 1, 2, 3 };
        int capacity = 4;

        System.out.println(new KnapSack().tabulation(profit, weight, capacity));
    }

}
