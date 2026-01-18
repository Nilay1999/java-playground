package BinarySearch;

/**
 * Find Smallest Divisor Given Threshold Algorithm:
 * Find the smallest divisor such that sum of ceil(nums[i]/divisor) <= threshold
 * 
 * BINARY SEARCH APPROACH:
 * 1. Search space: [1, max(nums)] - divisor must be at least 1
 * 2. For each divisor, calculate sum of ceil(nums[i]/divisor)
 * 3. If sum <= threshold: try smaller divisor (search left half)
 * 4. If sum > threshold: need larger divisor (search right half)
 * 
 * KEY INSIGHT: As divisor increases, sum decreases (monotonic property)
 * This allows binary search to find the boundary efficiently.
 * 
 * Example: nums=[1,2,5,9], threshold=6
 * divisor=5: ceil(1/5)+ceil(2/5)+ceil(5/5)+ceil(9/5) = 1+1+1+2 = 5 <= 6 ✓
 * divisor=4: ceil(1/4)+ceil(2/4)+ceil(5/4)+ceil(9/4) = 1+1+2+3 = 7 > 6 ✗
 * Answer: 5
 * 
 * Time: O(n * log(max)), Space: O(1)
 */
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
