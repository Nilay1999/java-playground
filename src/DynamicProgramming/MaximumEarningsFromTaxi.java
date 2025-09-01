package DynamicProgramming;

import java.util.Arrays;

public class MaximumEarningsFromTaxi {
    public static void main(String[] args) {
        int n = 20;
        int[][] rides = {{1, 6, 1}, {3, 10, 2}, {10, 12, 3}, {11, 12, 2}, {12, 15, 2}, {13, 18, 1}};

        System.out.println(new MaximumEarningsFromTaxi().maxTaxiEarnings(n, rides));
    }

    public long maxTaxiEarnings(int n, int[][] rides) {
        int len = rides.length;
        int[] profit = new int[len];

        Arrays.sort(rides, (a, b) -> a[0] - b[0]);

        for (int i = 0; i < len; i++) {
            profit[i] = rides[i][1] - rides[i][0] + rides[i][2];
        }
        Long[] dp = new Long[len];
        return memoization(profit, rides, dp, n, 0);
    }

    public long memoization(int[] profit, int[][] rides, Long[] dp, int maxPoint, int idx) {
        if (idx >= rides.length || idx < 0 || maxPoint < rides[idx][1]) {
            return 0;
        }

        if (dp[idx] != null) {
            return dp[idx];
        }

        int nextPickup = findNextPickUp(rides, idx);
        System.out.print("start: " + idx + " ");
        System.out.println("next: " + nextPickup);
        long take = profit[idx] + memoization(profit, rides, dp, maxPoint, nextPickup);
        long ignore = memoization(profit, rides, dp, maxPoint, idx + 1);

        dp[idx] = Math.max(take, ignore);
        return dp[idx];
    }

    private int findNextPickUp(int[][] rides, int current) {
        int left = current + 1;
        int right = rides.length - 1;
        int nextIdx = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (rides[mid][0] >= rides[current][1]) {
                nextIdx = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return nextIdx;
    }
}
