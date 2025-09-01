package DynamicProgramming;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaximumProfitInJobScheduling {
    public static void main(String[] args) {
        MaximumProfitInJobScheduling maxProfit = new MaximumProfitInJobScheduling();
        int[] startTime = {1, 2, 3, 4, 6};
        int[] endTime = {3, 5, 10, 6, 9};
        int[] profit = {20, 20, 100, 70, 60};

        System.out.println(maxProfit.jobScheduling(startTime, endTime, profit));
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Job> jobs = new ArrayList<>();
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            jobs.add(new Job(startTime[i], endTime[i], profit[i]));
        }
        jobs.sort(Comparator.comparingInt(a -> a.startTime));
        Integer[] dp = new Integer[n];
        return memoization(jobs, dp, 0);
    }

    private int memoization(List<Job> jobs, Integer[] dp, int idx) {
        int n = jobs.size();
        if (idx >= n || idx < 0) {
            return 0;
        }
        if (idx == n - 1) {
            return jobs.get(idx).profit;
        }

        if (dp[idx] != null) {
            return dp[idx];
        }
        int currentProfit = jobs.get(idx).profit;
        int nextJob = getNextJob(jobs, idx);

        int take = currentProfit + memoization(jobs, dp, nextJob);
        int ignore = memoization(jobs, dp, idx + 1);
        dp[idx] = Math.max(take, ignore);
        return dp[idx];
    }

    private int getNextJob(List<Job> jobs, int current) {
        int nextIndex = -1;
        int left = current + 1;
        int right = jobs.size() - 1;

        int endTime = jobs.get(current).endTime;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (jobs.get(mid).startTime >= endTime) {
                nextIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return nextIndex;
    }

    private static class Job {
        int startTime;
        int endTime;
        int profit;

        Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }

}
