package DynamicProgramming;

public class KnapSack {

    private int dfs(Integer[][] memo, int[] profit, int[] weight, int capacity, int idx) {
        if (capacity == 0 || idx == 0) {
            return 0;
        }

        if (memo[idx][capacity] != null) {
            return memo[idx][capacity];
        }

        if (capacity < weight[idx]) {
            memo[idx][capacity] = dfs(memo, profit, weight, capacity, idx - 1);
            return memo[idx][capacity];
        } else {
            int take = profit[idx] + dfs(memo, profit, weight, capacity - weight[idx], idx - 1);
            int skip = dfs(memo, profit, weight, capacity, idx - 1);
            memo[idx][capacity] = Math.max(take, skip);
            return memo[idx][capacity];
        }
    }

    private int memoization(int[] profit, int[] weight, int capacity) {
        int n = profit.length;
        Integer[][] memo = new Integer[n + 1][capacity + 1];

        return dfs(memo, profit, weight, capacity, n - 1);
    }

    public static void main(String[] args) {
        int[] weight = { 4, 5, 1 };
        int[] profit = { 1, 2, 3 };
        int capacity = 4;

        System.out.println(new KnapSack().memoization(profit, weight, capacity));
    }

}
