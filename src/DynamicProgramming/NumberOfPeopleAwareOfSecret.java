package DynamicProgramming;

class NumberOfPeopleAwareOfSecret {
    private static long MOD = (long) 1e9 + 7;

    private Long memoization(Long[] memo, int day, int delay, int forget) {
        if (day == 1) {
            return (long) 1;
        }
        if (memo[day] != null) {
            return memo[day];
        }
        int res = 0;
        for (int d = day - forget + 1; d <= day - delay; d++) {
            if (d > 0) {
                res = (int) (res + memoization(memo, d, delay, forget) % MOD);
            }
        }
        memo[day] = (long) res;
        return (long) res;
    }

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long res = 0;
        Long[] memo = new Long[n + 1];
        for (int d = n - forget + 1; d <= n; d++) {
            res = (res + memoization(memo, d, delay, forget) % MOD);
        }
        return (int) (res % MOD);
    }

    public int tabulation(int n, int delay, int forget) {
        Long[] dp = new Long[n + 1];
        dp[1] = (long) 1;

        for (int d = 2; d <= n; d++) {
            long res = 0;
            for (int i = d - forget + 1; i <= d - delay; i++) {
                res = (res + dp[i]) % MOD;
            }
            dp[d] = res;
        }

        long res = 0;
        for (int d = n - forget + 1; d <= n; d++) {
            res = (res + dp[d] % MOD);
        }
        return (int) (res % MOD);
    }

    public static void main(String[] args) {
        int n = 6, delay = 2, forget = 4;
        System.out.println(new NumberOfPeopleAwareOfSecret().peopleAwareOfSecret(n, delay, forget));
    }
}