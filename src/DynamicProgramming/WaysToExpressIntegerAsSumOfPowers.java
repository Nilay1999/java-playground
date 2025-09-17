package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WaysToExpressIntegerAsSumOfPowers {
    public static final int MOD = 1000000007;

    public static int recursion(List<Integer> numbers, int[][] dp, int i, int sum, int target) {
        if (sum >= target) {
            return target == sum ? 1 : 0;
        }

        if (i >= numbers.size()) {
            return 0;
        }

        if (dp[i][sum] != -1) {
            return dp[i][sum];
        }

        int take = 0;
        if (numbers.get(i) + sum <= target) {
            take = recursion(numbers, dp, i + 1, sum + numbers.get(i), target);
        }
        int skip = recursion(numbers, dp, i + 1, sum, target);

        dp[i][sum] = (skip + take) % MOD;
        return dp[i][sum];
    }

    public static int numberOfWays(int n, int x) {
        List<Integer> squares = new ArrayList<>();

        for (int i = 1; ; i++) {
            int val = (int) Math.pow(i, x);
            if (val > n) {
                break;
            }
            squares.add(val);
        }

        int[][] dp = new int[n][n];

        for (int[] ints : dp) {
            Arrays.fill(ints, -1);
        }

        return recursion(squares, dp, 0, 0, n);
    }

    public static void main(String[] args) {
        int n = 4;
        int x = 1;
        System.out.println(numberOfWays(n, x));
    }
}

// 1, 4, 9, 16, 25