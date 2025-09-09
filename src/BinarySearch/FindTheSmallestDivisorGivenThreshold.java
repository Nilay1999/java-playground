package BinarySearch;

public class FindTheSmallestDivisorGivenThreshold {
    private int findMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    private int getSum(int[] nums, int divisor) {
        int sum = 0;
        for (int num : nums) {
            sum += (int) Math.ceil(((double) num / (double) divisor));
        }
        return sum;
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int left = 1;
        int right = findMax(nums);

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int sum = getSum(nums, mid);
            if (sum <= threshold) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 5, 9 };
        int threshold = 6;

        System.out.println(new FindTheSmallestDivisorGivenThreshold().smallestDivisor(nums, threshold));
    }
}
