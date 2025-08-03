package DynamicProgramming;

import java.util.Arrays;

public class MinimumCostToCutLog {

    // DFS + Memoization
    private int dfs(int l, int r, int[] arr, int[][] memo) {
        if (r - l < 2) return 0;
        if (memo[l][r] != -1) return memo[l][r];

        int cost = Integer.MAX_VALUE;
        for (int i = l + 1; i < r; i++) {
            int currentCost = arr[r] - arr[l];
            int left = dfs(l, i, arr, memo);
            int right = dfs(i, r, arr, memo);
            cost = Math.min(cost, left + right + currentCost);
        }

        memo[l][r] = cost;
        return cost;
    }

    public int recursion(int n, int[] cuts) {
        int m = cuts.length + 2;
        int[] newCuts = new int[m];
        newCuts[0] = 0;
        System.arraycopy(cuts, 0, newCuts, 1, cuts.length);
        newCuts[m - 1] = n;
        Arrays.sort(newCuts);

        int[][] memo = new int[m][m];
        for (int[] row : memo) Arrays.fill(row, -1);

        return dfs(0, m - 1, newCuts, memo);
    }

    // Bottom-up DP
    public int minCost(int n, int[] cuts) {
        int m = cuts.length + 2;
        int[] newCuts = new int[m];
        newCuts[0] = 0;
        System.arraycopy(cuts, 0, newCuts, 1, cuts.length);
        newCuts[m - 1] = n;
        Arrays.sort(newCuts);

        int[][] dp = new int[m][m];

        for (int gap = 2; gap < m; gap++) {
            for (int left = 0; left + gap < m; left++) {
                int right = left + gap;
                int minCost = Integer.MAX_VALUE;

                for (int k = left + 1; k < right; k++) {
                    int leftCost = dp[left][k];
                    int rightCost = dp[k][right];
                    int cost = newCuts[right] - newCuts[left];
                    minCost = Math.min(minCost, leftCost + rightCost + cost);
                }

                dp[left][right] = minCost;
            }
        }

        return dp[0][m - 1];
    }

    public static void main(String[] args) {
        int logLength = 7;
        int[] logCuts = {1, 3, 4, 5};

        MinimumCostToCutLog solver = new MinimumCostToCutLog();

        System.out.println("Tabulation: " + solver.minCost(logLength, logCuts));      // Output: 16
        System.out.println("Memoization: " + solver.recursion(logLength, logCuts));   // Output: 16
    }
}

