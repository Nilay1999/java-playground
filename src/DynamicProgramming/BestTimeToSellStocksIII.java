package DynamicProgramming;

public class BestTimeToSellStocksIII {
    private int dfs(int idx, int buy, int maxT, int[] prices, int[][][] dp) {
        if (idx == prices.length || maxT == 0) {
            return 0;
        }

        if (dp[idx][buy][maxT] != -1) {
            return dp[idx][buy][maxT];
        }

        if (buy == 1) {
            int take = -1 * prices[idx] + dfs(idx + 1, 0, maxT, prices, dp);
            int ignore = dfs(idx + 1, 1, maxT, prices, dp);
            dp[idx][buy][maxT] = Math.max(take, ignore);
            return dp[idx][buy][maxT];
        } else {
            int sell = prices[idx] + dfs(idx + 1, 1, maxT - 1, prices, dp);
            int ignore = dfs(idx + 1, 0, maxT, prices, dp);
            dp[idx][buy][maxT] = Math.max(sell, ignore);
            return dp[idx][buy][maxT];
        }

    }

    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][3];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        return dfs(0, 1, 2, prices, dp);
    }

    public int maxProfitTabulation(int[] prices) {
        int n = prices.length;
        int k = 2;
        int[][][] dp = new int[n + 1][2][k + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0][0] = 0;
            dp[i][1][0] = 0;
        }

        for (int j = 0; j < 2; j++) {
            for (int l = 0; l <= k; l++) {
                dp[n][j][l] = 0;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int buy = 0; buy < 2; buy++) {
                for (int t = 1; t <= k; t++) {
                    if (buy == 1) {
                        int take = -1 * prices[i] + dp[i + 1][0][t];
                        int move = dp[i + 1][1][t];
                        dp[i][buy][t] = Math.max(take, move);
                    } else {
                        int take = prices[i] + dp[i + 1][1][t - 1];
                        int move = dp[i + 1][0][t];
                        dp[i][buy][t] = Math.max(take, move);
                    }
                }
            }
        }

        return dp[0][1][k];
    }

    public static void main(String[] args) {
        int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
        System.out.println(new BestTimeToSellStocksIII().maxProfit(prices));
    }
}
