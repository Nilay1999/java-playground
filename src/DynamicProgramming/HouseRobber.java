package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HouseRobber {
    private final Map<Integer, Integer> memo;

    public HouseRobber() {
        this.memo = new HashMap<>();
    }

    private int recursion(int idx, int[] arr) {
        if (memo.containsKey(idx)) {
            return memo.get(idx);
        }

        if (idx == 0) {
            return arr[0];
        }

        if (idx < 0) {
            return 0;
        }

        int take = arr[idx] + recursion(idx - 2, arr);
        int skip = recursion(idx - 1, arr);

        int res = Math.max(take, skip);
        memo.put(idx, res);
        return res;
    }

    public int memoization(int[] houses) {
        int n = houses.length;
        if (n == 0) return 0;
        if (n == 1) return houses[0];
        if (n == 2) return Math.max(houses[0], houses[1]);

        memo.clear(); // Ensure fresh run
        return recursion(n - 1, houses);
    }

    public int tabulation(int[] houses) {
        int n = houses.length;
        if (n == 0) return 0;
        if (n == 1) return houses[0];
        if (n == 2) return Math.max(houses[0], houses[1]);

        int[] dp = new int[n];
        dp[0] = houses[0];
        dp[1] = Math.max(houses[0], houses[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + houses[i]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] houses = {1, 4, 5, 7, 2, 1, 9};
        HouseRobber houseRobber = new HouseRobber();

        System.out.println("Memoization: " + houseRobber.memoization(houses));  // Output: 21
        System.out.println("Tabulation: " + houseRobber.tabulation(houses));    // Output: 21
    }
}

