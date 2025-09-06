package DynamicProgramming;

public class DiceCombinations {
    static final int MOD = 1_000_000_007;

    public int recursion(int target) {
        if (target < 0) {
            return 0;
        }
        if (target == 0) {
            return 1;
        }

        int total = 0;
        for (int i = 1; i <= 6; i++) {
            total += recursion(target - i);
        }

        return total;
    }

    public int memoization(int n) {
        return recursion(n);
    }

    public int tabulation(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i < n + 1; i++) {
            for (int s = 1; s <= 6; s++) {
                if (i - s >= 0) {
                    dp[i] += (dp[i - 1] + dp[i - s]) % MOD;
                }
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(new DiceCombinations().memoization(n));
    }
}
