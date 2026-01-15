package company.Amazon;

public class MaximumSubarray {
    public int kadane(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int total = -1;
        for (int i = 0; i < n; i++) {
            total += nums[i];
            if (total > max) {
                max = total;
            }

            if (total < 0) {
                total = 0;
            }
        }
        return max;
    }

    public int bruteForce(int[] nums) {
        int n = nums.length;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int total = 0;
            for (int j = i; j < n; j++) {
                total += nums[j];
                max = Math.max(total, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumSubarray().kadane(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
        System.out.println(new MaximumSubarray().bruteForce(new int[] { -2, 1, -3, 4, -1, 2, 1, -5, 4 }));
    }
}
